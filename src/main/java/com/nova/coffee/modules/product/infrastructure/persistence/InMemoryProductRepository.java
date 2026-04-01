package com.nova.coffee.modules.product.infrastructure.persistence;

import com.nova.coffee.modules.product.domain.model.CoffeeProduct;
import com.nova.coffee.modules.product.domain.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * 内存版商品仓储。
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final List<CoffeeProduct> products = List.of(
        new CoffeeProduct("LATTE", "拿铁", new BigDecimal("28.00"), true),
        new CoffeeProduct("AMERICANO", "美式", new BigDecimal("22.00"), true),
        new CoffeeProduct("MOCHA", "摩卡", new BigDecimal("32.00"), true),
        new CoffeeProduct("SEASONAL", "季节限定特调", new BigDecimal("36.00"), false)
    );

    @Override
    public List<CoffeeProduct> findAllAvailable() {
        return products.stream().filter(CoffeeProduct::available).toList();
    }

    @Override
    public Optional<CoffeeProduct> findByCode(String productCode) {
        return products.stream()
            .filter(product -> product.productCode().equalsIgnoreCase(productCode))
            .findFirst();
    }
}
