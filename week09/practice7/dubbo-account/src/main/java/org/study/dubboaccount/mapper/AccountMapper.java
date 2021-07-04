package org.study.dubboaccount.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.study.dubboapi.vo.AccountDTO;


@Mapper
@Repository
public interface AccountMapper {

    @Update("update account set balance = balance - #{amount}," +
            " update_time = now()" +
            " where user_id =#{userId}  and  balance > 0  ")
    int update(AccountDTO accountDTO);

    @Update("update account set ready=1 where user_id =#{userId}  and ready=0 ")
    int confirm(AccountDTO accountDTO);

    @Update("update account set balance = balance + #{amount} where user_id =#{userId} and ready=0")
    int cancel(AccountDTO accountDTO);
}
