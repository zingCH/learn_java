/**
 * <b>工程名：</b>gateway<br/>
 * <b>包  名：</b>org.java.study.gateway.filter<br/>
 * <b>文件名：</b>HttpRequestFilter.java<br/>
 * <b>日  期：</b>2021/05/23<br/>
 */
package org.java.study.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * <b>类  名：</b>HttpRequestFilter<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/23<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public interface HttpRequestFilter {

    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
