/**
 * <b>工程名：</b>week08<br/>
 * <b>包  名：</b>com.example.demo.service<br/>
 * <b>文件名：</b>OrderService.java<br/>
 * <b>日  期：</b>2021/06/27<br/>
 */
package com.example.demo.service;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b>类  名：</b>OrderService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/27<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;


    public List<Order> listByUid(Long uid) {

        return orderDao.listByUid(uid);
    }


    public Order findByOrderIdAndUid(Long uid, String orderId) {
        return orderDao.findByOrderIdAndUid(uid, orderId);
    }


    public boolean insertOrder(Order order) {


        return orderDao.insertOrder(order);
    }


    public boolean updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }


    public boolean deleteByOrderId(String orderId,Long uid) {
        return orderDao.deleteByOrderId(orderId,uid);
    }

    public List<Order> list() {
        return orderDao.list();
    }
}
