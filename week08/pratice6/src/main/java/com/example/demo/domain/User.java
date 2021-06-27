package com.example.demo.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @creator fanglc@anch.net
 * @createdTime 2020/12/12 14:08
 * @desc
 */
@Data
public class User {

    private int id;

    private String name;

    private String password;

    private String telNo;

    private String idNo;

    private BigDecimal balance;
}
