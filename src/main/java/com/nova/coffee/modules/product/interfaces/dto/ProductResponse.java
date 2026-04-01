package com.nova.coffee.modules.product.interfaces.dto;

import java.math.BigDecimal;

/**
 * 商品响应对象。
 */
public record ProductResponse(
    String productCode,
    String name,
    BigDecimal price
) {
}
