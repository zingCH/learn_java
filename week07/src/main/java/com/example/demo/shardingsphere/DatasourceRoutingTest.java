/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.shardingsphere<br/>
 * <b>文件名：</b>DatasourceRoutingTest.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 *//*

package com.example.demo.shardingsphere;

import com.example.demo.dao.OrderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.OrderEntity;
//import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

*/
/**
 * <b>类  名：</b>DatasourceRoutingTest<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 *//*

//@SpringBootTest
public class DatasourceRoutingTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;

    @Test
    public void test()
    {
        HintManager hintManager = HintManager.getInstance();

        hintManager.setDatabaseShardingValue(DatasourceType.DATASOURCE_USER.toString());
        // 访问用户数据源
        String username = userDao.getUserNameByUserId(3);
        System.out.println("用户名查询结果:" + username);
        hintManager.close();

        hintManager.setDatabaseShardingValue(DatasourceType.DATASOURCE_ORDER.toString());
        // 访问订单数据源
        OrderEntity order = orderDao.selectByOid("1");
        System.out.println("订单查询结果:" + order);
        hintManager.close();
    }

}
*/
