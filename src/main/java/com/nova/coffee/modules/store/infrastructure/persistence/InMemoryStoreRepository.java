package com.nova.coffee.modules.store.infrastructure.persistence;

import com.nova.coffee.modules.store.domain.model.StoreInfo;
import com.nova.coffee.modules.store.domain.repository.StoreRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 内存版门店仓储。
 */
@Repository
public class InMemoryStoreRepository implements StoreRepository {

    @Override
    public List<StoreInfo> findAll() {
        return List.of(
            new StoreInfo(1L, "上海静安店", "08:00-22:00", "上海市静安区南京西路 100 号"),
            new StoreInfo(2L, "杭州西湖店", "09:00-21:30", "杭州市西湖区湖滨路 88 号")
        );
    }
}
