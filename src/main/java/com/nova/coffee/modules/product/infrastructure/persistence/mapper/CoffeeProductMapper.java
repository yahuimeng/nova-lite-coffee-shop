package com.nova.coffee.modules.product.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.coffee.modules.product.infrastructure.persistence.dataobject.CoffeeProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品 Mapper。
 */
@Mapper
public interface CoffeeProductMapper extends BaseMapper<CoffeeProductEntity> {
}
