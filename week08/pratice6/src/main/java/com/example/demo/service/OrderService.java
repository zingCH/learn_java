package com.example.demo.service;;

import com.example.demo.domain.Order;
import com.example.demo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public long save(final Order order) {
        orderMapper.save(order);
        return order.getId();
    }

    public Order findOrderById(final long id) {
        return orderMapper.selectOrderById(id);
    }

}
