package com.nova.coffee.modules.cart.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.coffee.modules.cart.infrastructure.persistence.dataobject.CartItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车项 Mapper。
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItemEntity> {
}
