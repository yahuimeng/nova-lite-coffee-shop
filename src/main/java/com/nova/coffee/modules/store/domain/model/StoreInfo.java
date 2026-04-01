package com.nova.coffee.modules.store.domain.model;

/**
 * 门店信息。
 */
public record StoreInfo(
    Long storeId,
    String storeName,
    String businessHours,
    String address
) {
}
