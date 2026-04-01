package com.nova.coffee.modules.system.application;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nova.coffee.modules.product.infrastructure.persistence.dataobject.CoffeeProductEntity;
import com.nova.coffee.modules.product.infrastructure.persistence.mapper.CoffeeProductMapper;
import org.springframework.stereotype.Component;

/**
 * 商品持久层探针。
 *
 * <p>把系统基础状态探测与商品模块的 Mapper 使用隔离开，避免 Controller 直接跨层访问。
 */
@Component
public class ProductPersistenceProbe {

    private final CoffeeProductMapper coffeeProductMapper;

    public ProductPersistenceProbe(CoffeeProductMapper coffeeProductMapper) {
        this.coffeeProductMapper = coffeeProductMapper;
    }

    public long countProducts() {
        return coffeeProductMapper.selectCount(new LambdaQueryWrapper<>());
    }
}
