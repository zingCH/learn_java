package org.study.dubboapi.service;

import org.dromara.hmily.annotation.Hmily;
import org.study.dubboapi.vo.AccountDTO;

public interface AccountService {

    @Hmily
    boolean exchange(AccountDTO accountDTO);
}
