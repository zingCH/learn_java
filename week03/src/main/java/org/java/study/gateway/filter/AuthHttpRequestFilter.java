/**
 * <b>工程名：</b>gateway<br/>
 * <b>包  名：</b>org.java.study.gateway.filter<br/>
 * <b>文件名：</b>AuthHttpRequestFilter.java<br/>
 * <b>日  期：</b>2021/05/23<br/>
 */
package org.java.study.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>类  名：</b>AuthHttpRequestFilter<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/23<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Slf4j
public class AuthHttpRequestFilter implements AuthRequestFilter{
    @Override
    public boolean isAuth(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        try {
            Map<String, String> parse = parse(fullRequest);
            if(parse==null||parse.isEmpty()||!parse.containsKey("token")){
                log.warn("无权访问");
                return false;
            }else {
                String token = parse.get("token");
                log.info(token);
                //识别token
            }
        } catch (IOException e) {
            log.error("鉴权异常 {0}",e);
            return false;
        }
        return true;
    }

    public  Map<String, String> parse(FullHttpRequest fullRequest) throws IOException {
        HttpMethod method = fullRequest.method();

        Map<String, String> parmMap = new HashMap<>();

        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(fullRequest.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullRequest);
            decoder.offer(fullRequest);
            List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();
            for (InterfaceHttpData parm : parmList) {
                Attribute data = (Attribute) parm;
                parmMap.put(data.getName(), data.getValue());
            }
        } else {
            // 不支持其它方法

        }
        return parmMap;
    }
}
