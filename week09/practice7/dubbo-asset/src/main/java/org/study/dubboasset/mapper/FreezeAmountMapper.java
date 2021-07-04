package org.study.dubboasset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.study.dubboapi.vo.FreezeAssetDTO;

@Mapper
@Repository
public interface FreezeAmountMapper {

    @Update("update freeze_amount set freeze_amount= freeze_amount + #{amount},update_time = now() where user_id =#{userId}  and account_id=#{accountId} ")
    int updateTempConfirm(FreezeAssetDTO freezeAssetDTO);

    @Update("update freeze_amount set freeze_amount= freeze_amount - #{amount},update_time = now() where user_id =#{userId}  and account_id=#{accountId}")
    int updateTempRollBack(FreezeAssetDTO freezeAssetDTO);



    @Update("update freeze_amount set freeze_amount= freeze_amount + #{amount} where user_id =#{userId}  and account_id=#{accountId}")
    int confirm(FreezeAssetDTO freezeAssetDTO);


    @Update("update freeze_amount set freeze_amount= freeze_amount - #{amount} where user_id =#{userId}  and account_id=#{accountId}")
    int cancel(FreezeAssetDTO freezeAssetDTO);
}

