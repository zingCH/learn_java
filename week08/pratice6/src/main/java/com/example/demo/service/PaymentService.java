package com.example.demo.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.example.demo.domain.Goods;
import com.example.demo.domain.Order;
import com.example.demo.dto.GoodsStockDTO;
import com.example.demo.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Slf4j
@Service
public class PaymentService{

    private final Snowflake ID = IdUtil.getSnowflake(1, 1);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public long payOrder(OrderDTO orderDTO) {
        Goods goods = goodsService.selectById(orderDTO.getGoodsId());
        Order order = doCreateOrder(orderDTO, goods);
        orderService.save(order);
        orderDTO.setOrderId(order.getId());
        GoodsStockDTO goodsStockDTO = createGoodsStockDTO(orderDTO);
        goodsService.buy(goodsStockDTO);
        return order.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public long paymentWithBuyException(OrderDTO orderDTO) {
        Goods goods = goodsService.selectById(orderDTO.getGoodsId());
        Order order = doCreateOrder(orderDTO, goods);
        orderService.save(order);
        orderDTO.setOrderId(order.getId());
        GoodsStockDTO goodsStockDTO = createGoodsStockDTO(orderDTO);
        goodsService.buyException(goodsStockDTO);
        return order.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public long paymentWithException(OrderDTO orderDTO) {
        Goods goods = goodsService.selectById(orderDTO.getGoodsId());
        Order order = doCreateOrder(orderDTO, goods);
        orderService.save(order);
        orderDTO.setOrderId(order.getId());
        GoodsStockDTO goodsStockDTO = createGoodsStockDTO(orderDTO);
        goodsService.buy(goodsStockDTO);
        throw new IllegalStateException("pay exception......");
    }

    private GoodsStockDTO createGoodsStockDTO(OrderDTO orderDTO) {
        GoodsStockDTO goodsStockDTO = new GoodsStockDTO();
        goodsStockDTO.setGoodsId(orderDTO.getGoodsId());
        goodsStockDTO.setQuantity(orderDTO.getQuantity());
        return goodsStockDTO;
    }

    private Order doCreateOrder(OrderDTO orderDTO, Goods goods) {
        Order order = new Order();
        order.setOrderSn(ID.nextIdStr());
        order.setMemberId(ID.nextId());
        order.setGoodsId(orderDTO.getGoodsId());
        order.setGoodsName(goods.getName());
        order.setPrice(goods.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalAmount(BigDecimal.valueOf(orderDTO.getQuantity()).multiply(goods.getPrice()));
        order.setStatus(1);
        return order;
    }
}
