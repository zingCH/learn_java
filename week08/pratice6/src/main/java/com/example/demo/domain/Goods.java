package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhongjinhui
 */
@Data
public class Goods implements Serializable {

    private long id;

    private String name;

    private long stock;

    private BigDecimal price;

}