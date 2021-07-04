package org.study.dubboapi.service;

import org.dromara.hmily.annotation.Hmily;
import org.study.dubboapi.vo.FreezeAssetDTO;

public interface FreezeAssetService {

    @Hmily
    boolean updateTempConfirm(FreezeAssetDTO freezeAssetDTO);

    @Hmily
    boolean updateTempRollback(FreezeAssetDTO freezeAssetDTO);
}
