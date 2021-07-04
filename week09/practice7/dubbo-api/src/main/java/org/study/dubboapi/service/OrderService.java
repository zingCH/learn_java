package org.study.dubboapi.service;


import org.study.dubboapi.bean.Order;

public interface OrderService {

    Order findOrderById(int id);
}
