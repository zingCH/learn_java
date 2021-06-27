package com.example.demo.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {

    private int id;

    private String name;

    private String password;

    private String telNo;

    private String idNo;

    private BigDecimal balance;
}
