package com.nova.coffee.modules.order.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.coffee.modules.order.infrastructure.persistence.dataobject.CoffeeOrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper。
 */
@Mapper
public interface CoffeeOrderMapper extends BaseMapper<CoffeeOrderEntity> {
}
