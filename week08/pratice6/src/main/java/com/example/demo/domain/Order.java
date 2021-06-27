package com.example.demo.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private long id;

    private long memberId;

    private String orderSn;

    private long goodsId;

    private String goodsName;

    private int quantity;

    private BigDecimal price;

    private BigDecimal totalAmount;

    private int status;

}