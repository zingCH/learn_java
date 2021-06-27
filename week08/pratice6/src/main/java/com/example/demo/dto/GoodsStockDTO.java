package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * GoodsStockDTO
 *
 * @author zhongjinhui
 */
@Data
public class GoodsStockDTO implements Serializable {
    private Long goodsId;
    private Integer quantity;
}
