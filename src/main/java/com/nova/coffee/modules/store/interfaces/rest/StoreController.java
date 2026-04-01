package com.nova.coffee.modules.store.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.store.application.StoreApplicationService;
import com.nova.coffee.modules.store.interfaces.dto.StoreResponse;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门店接口。
 */
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreApplicationService storeApplicationService;

    public StoreController(StoreApplicationService storeApplicationService) {
        this.storeApplicationService = storeApplicationService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<List<StoreResponse>> listStores() {
        return Result.success(storeApplicationService.listStores());
    }
}
