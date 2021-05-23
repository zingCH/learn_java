/**
 * <b>工程名：</b>gateway<br/>
 * <b>包  名：</b>org.java.study.gateway.router<br/>
 * <b>文件名：</b>RandomHttpEndpointRouter.java<br/>
 * <b>日  期：</b>2021/05/23<br/>
 */
package org.java.study.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * <b>类  名：</b>RandomHttpEndpointRouter<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/23<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
