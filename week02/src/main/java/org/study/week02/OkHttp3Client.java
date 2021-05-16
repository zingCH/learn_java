/**
 * <b>工程名：</b>week02<br/>
 * <b>包  名：</b>org.study.week02<br/>
 * <b>文件名：</b>OkHttp3Client.java<br/>
 * <b>日  期：</b>2021/05/16<br/>
 */
package org.study.week02;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * <b>类  名：</b>OkHttpClient<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/16<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class OkHttp3Client {
    // 缓存客户端实例
    public static OkHttpClient client = new OkHttpClient();

    // GET 调用
    public static String getAsString(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public static void main(String[] args) throws Exception {

        String url = "http:localhost:8801/";
        String text = getAsString(url);
        System.out.println("url: " + url + " ; response: \n" + text);

        // 清理资源
        client = null;
    }
}
