package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class GoodsStockDTO implements Serializable {
    private Long goodsId;
    private Integer quantity;
}
