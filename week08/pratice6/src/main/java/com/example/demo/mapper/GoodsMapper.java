package com.example.demo.mapper;

import com.example.demo.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsMapper {


    void save(@Param("goods") Goods goods);


    Goods selectById(long id);


    void reduceStock(long id, int quantity);

}
