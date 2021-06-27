package com.example.demo.mapper;

import com.example.demo.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface OrderMapper {

    void save(@Param("order") Order order);


    Order selectOrderById(long id);

}
