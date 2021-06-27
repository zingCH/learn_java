package com.example.demo.dto;

import lombok.Data;


@Data
public class OrderDTO {
    private Long goodsId;
    private Integer quantity;
    private Long orderId;
}
