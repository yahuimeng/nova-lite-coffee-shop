package com.nova.coffee.modules.store.application;

import com.nova.coffee.modules.store.domain.repository.StoreRepository;
import com.nova.coffee.modules.store.interfaces.dto.StoreResponse;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 门店应用服务。
 */
@Service
public class StoreApplicationService {

    private final StoreRepository storeRepository;

    public StoreApplicationService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<StoreResponse> listStores() {
        return storeRepository.findAll().stream()
            .map(store -> new StoreResponse(store.storeId(), store.storeName(), store.businessHours(), store.address()))
            .toList();
    }
}
