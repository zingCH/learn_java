package org.study.dubboprovider;


import org.apache.dubbo.config.annotation.DubboService;
import org.study.dubboapi.bean.Order;
import org.study.dubboapi.service.OrderService;


@DubboService(version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
