package com.nova.coffee.modules.store.interfaces.dto;

/**
 * 门店响应对象。
 */
public record StoreResponse(
    Long storeId,
    String storeName,
    String businessHours,
    String address
) {
}
