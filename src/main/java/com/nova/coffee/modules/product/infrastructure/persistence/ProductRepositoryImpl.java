package com.nova.coffee.modules.product.infrastructure.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nova.coffee.modules.product.domain.model.CoffeeProduct;
import com.nova.coffee.modules.product.domain.repository.ProductRepository;
import com.nova.coffee.modules.product.infrastructure.persistence.dataobject.CoffeeProductEntity;
import com.nova.coffee.modules.product.infrastructure.persistence.mapper.CoffeeProductMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * 商品仓储实现。
 *
 * <p>优先读取 MySQL；如果本地还没初始化表数据，则回退到内存演示数据，保证学习时可以无痛启动。
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final CoffeeProductMapper coffeeProductMapper;

    private static final List<CoffeeProduct> DEMO_PRODUCTS = List.of(
        new CoffeeProduct("LATTE", "拿铁", new BigDecimal("28.00"), true),
        new CoffeeProduct("AMERICANO", "美式", new BigDecimal("22.00"), true),
        new CoffeeProduct("MOCHA", "摩卡", new BigDecimal("32.00"), true),
        new CoffeeProduct("SEASONAL", "季节限定特调", new BigDecimal("36.00"), false)
    );

    public ProductRepositoryImpl(CoffeeProductMapper coffeeProductMapper) {
        this.coffeeProductMapper = coffeeProductMapper;
    }

    @Override
    public List<CoffeeProduct> findAllAvailable() {
        try {
            List<CoffeeProductEntity> entities = coffeeProductMapper.selectList(
                new LambdaQueryWrapper<CoffeeProductEntity>()
                    .eq(CoffeeProductEntity::getAvailable, 1)
            );
            if (entities == null || entities.isEmpty()) {
                return DEMO_PRODUCTS.stream().filter(CoffeeProduct::available).toList();
            }
            return entities.stream().map(this::toDomain).toList();
        } catch (Exception exception) {
            return DEMO_PRODUCTS.stream().filter(CoffeeProduct::available).toList();
        }
    }

    @Override
    public Optional<CoffeeProduct> findByCode(String productCode) {
        try {
            CoffeeProductEntity entity = coffeeProductMapper.selectOne(
                new LambdaQueryWrapper<CoffeeProductEntity>()
                    .eq(CoffeeProductEntity::getProductCode, productCode)
                    .last("limit 1")
            );
            if (entity != null) {
                return Optional.of(toDomain(entity));
            }
        } catch (Exception exception) {
            // 数据库还未准备好时，回退到演示数据，保证学习链路不中断。
        }
        return DEMO_PRODUCTS.stream()
            .filter(product -> product.productCode().equalsIgnoreCase(productCode))
            .findFirst();
    }

    private CoffeeProduct toDomain(CoffeeProductEntity entity) {
        return new CoffeeProduct(
            entity.getProductCode(),
            entity.getName(),
            entity.getPrice(),
            Integer.valueOf(1).equals(entity.getAvailable())
        );
    }
}
