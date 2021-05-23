/**
 * <b>工程名：</b>gateway<br/>
 * <b>包  名：</b>org.java.study.gateway<br/>
 * <b>文件名：</b>NettyServerApplication.java<br/>
 * <b>日  期：</b>2021/05/23<br/>
 */
package org.java.study.gateway;

import org.java.study.gateway.inbound.HttpInboundServer;

import java.util.Arrays;

/**
 * <b>类  名：</b>NettyServerApplication<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/23<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class NettyServerApplication {
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort","8888");

        // 这是之前的单个后端url的例子
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
//          //  http://localhost:8888/api/hello  ==> gateway API
//          //  http://localhost:8088/api/hello  ==> backend service
        // java -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar  #作为后端服务


        // 这是多个后端url走随机路由的例子
        String proxyServers = System.getProperty("proxyServers","http://localhost:8081,http://localhost:8082");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
