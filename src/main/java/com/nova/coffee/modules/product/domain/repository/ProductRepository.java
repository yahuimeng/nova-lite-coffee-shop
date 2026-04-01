package com.nova.coffee.modules.product.domain.repository;

import com.nova.coffee.modules.product.domain.model.CoffeeProduct;
import java.util.List;
import java.util.Optional;

/**
 * 商品仓储接口。
 */
public interface ProductRepository {

    List<CoffeeProduct> findAllAvailable();

    Optional<CoffeeProduct> findByCode(String productCode);
}
