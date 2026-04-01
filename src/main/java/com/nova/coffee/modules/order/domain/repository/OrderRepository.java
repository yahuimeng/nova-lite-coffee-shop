package com.nova.coffee.modules.order.domain.repository;

import com.nova.coffee.modules.order.domain.model.CoffeeOrder;
import java.util.Optional;

/**
 * 订单仓储接口。
 */
public interface OrderRepository {

    void save(CoffeeOrder order);

    Optional<CoffeeOrder> findByOrderNo(String orderNo);
}
