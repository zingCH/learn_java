/**
 * <b>工程名：</b>week08<br/>
 * <b>包  名：</b>com.example.demo.controller<br/>
 * <b>文件名：</b>OrderController.java<br/>
 * <b>日  期：</b>2021/06/27<br/>
 */
package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.apache.shardingsphere.core.spi.algorithm.keygen.ShardingKeyGeneratorServiceLoader;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>类  名：</b>OrderController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/27<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单列表
     * @param uid
     * @return
     */
    @GetMapping("/listsByUserId")
    public List<Order> lists(@RequestParam("uid") Long uid) {
        List<Order> orders = orderService.listByUid(uid);
        return orders;
    }
    

    /**
     * 查看订单详情
     * @param uid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public Order detail(@RequestParam("uid") Long uid,
                        @RequestParam("orderId") String orderId) {

        Order byOrderIdAndUid = orderService.findByOrderIdAndUid(uid, orderId);

        return byOrderIdAndUid;

    }

    /**
     * 创建订单
     * @param uid
     * @return
     */
    @GetMapping("/create")
    public String create(@RequestParam("uid") Long uid) {
        Order order = new Order();
        order.setUserId(uid);
        order.setDesc("订单描述");

        boolean b = orderService.insertOrder(order);

        return b ? "success" : "error";

    }


    /**
     * 修改订单
     * @return
     */
    @GetMapping("/update")
    public String create(@RequestParam("oid") String oid,@RequestParam("uid") Long uid,@RequestParam("desc") String desc) {
        Order order = new Order();
        order.setId(oid);
        order.setUserId(uid);
        order.setDesc(desc);

        boolean b = orderService.updateOrder(order);

        return b ? "success" : "error";

    }

    /**
     * 删除订单
     * @return
     */
    @GetMapping("/delete")
    public String create(@RequestParam("oid") String oid,@RequestParam("uid") Long uid) {

        boolean b = orderService.deleteByOrderId(oid,uid);

        return b ? "success" : "error";

    }
}
