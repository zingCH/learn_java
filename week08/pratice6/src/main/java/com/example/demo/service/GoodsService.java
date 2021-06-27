package com.example.demo.service;



import com.example.demo.domain.Goods;
import com.example.demo.dto.GoodsStockDTO;
import com.example.demo.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
@Component
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;


    public Goods selectById(long id) {
        return goodsMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void buy(GoodsStockDTO goodsStockDTO) {
        doBuy(goodsStockDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void buyException(GoodsStockDTO goodsStockDTO) {
        doBuy(goodsStockDTO);
        throw new IllegalStateException("--->> buy exception......");
    }

    private void doBuy(GoodsStockDTO goodsStockDTO) {
        Goods goods = goodsMapper.selectById(goodsStockDTO.getGoodsId());
        if (goods.getStock() < goodsStockDTO.getQuantity()) {
            throw new IllegalArgumentException("insufficient inventory of goods");
        }
        goodsMapper.reduceStock(goodsStockDTO.getGoodsId(), goodsStockDTO.getQuantity());
        log.info("--->>buy, goods id = {}, quantity = {}", goodsStockDTO.getGoodsId(), goodsStockDTO.getQuantity());
    }
}
