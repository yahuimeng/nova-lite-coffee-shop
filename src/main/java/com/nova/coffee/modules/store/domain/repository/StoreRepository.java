package com.nova.coffee.modules.store.domain.repository;

import com.nova.coffee.modules.store.domain.model.StoreInfo;
import java.util.List;

/**
 * 门店仓储接口。
 */
public interface StoreRepository {

    List<StoreInfo> findAll();
}
