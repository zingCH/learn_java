/**
 * <b>工程名：</b>gateway<br/>
 * <b>包  名：</b>org.java.study.gateway.outbound.okhttp<br/>
 * <b>文件名：</b>OkhttpOutboundHandler.java<br/>
 * <b>日  期：</b>2021/05/23<br/>
 */
package org.java.study.gateway.outbound.okhttp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import okhttp3.*;
import org.java.study.gateway.filter.HttpRequestFilter;
import org.java.study.gateway.filter.HttpResponseFilter;
import org.java.study.gateway.filter.TextHttpResponseFilter;
import org.java.study.gateway.outbound.httpcilent4.NamedThreadFactory;
import org.java.study.gateway.router.HttpEndpointRouter;
import org.java.study.gateway.router.RandomHttpEndpointRouter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * <b>类  名：</b>OkhttpOutboundHandler<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/23<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class OkhttpOutboundHandler {

    private OkHttpClient httpclient = new OkHttpClient();
    private ExecutorService proxyService;
    private List<String> backendUrls;

    //自定义过滤器
    HttpResponseFilter filter = new TextHttpResponseFilter();//new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public OkhttpOutboundHandler(List<String> backends) {

        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        //路由到具体的后端服务
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        //过滤请求
        filter.filter(fullRequest, ctx);
        //提交到线程池处理请求
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        Request request = new Request.Builder().addHeader("mykey","myKey")
                .url(url)
                .build();
       httpclient.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(@NotNull Call call, @NotNull IOException e) {

           }

           @Override
           public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handleResponse(inbound,ctx,response);
           }
       });
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse) {
        FullHttpResponse response = null;
        try {
            String s = endpointResponse.body().toString();
            byte[] body = endpointResponse.body().toString().getBytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.header("Content-Length")));
            //过滤响应
            filter.filter(response);
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
