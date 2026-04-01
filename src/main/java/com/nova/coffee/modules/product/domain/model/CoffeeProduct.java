package com.nova.coffee.modules.product.domain.model;

import java.math.BigDecimal;

/**
 * 咖啡商品实体。
 */
public record CoffeeProduct(
    String productCode,
    String name,
    BigDecimal price,
    boolean available
) {
}
