package com.nova.coffee.modules.order.infrastructure.persistence;

import com.nova.coffee.modules.order.domain.model.CoffeeOrder;
import com.nova.coffee.modules.order.domain.repository.OrderRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 内存版订单仓储。
 */
@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, CoffeeOrder> storage = new ConcurrentHashMap<>();

    @Override
    public void save(CoffeeOrder order) {
        storage.put(order.getOrderNo(), order);
    }

    @Override
    public Optional<CoffeeOrder> findByOrderNo(String orderNo) {
        return Optional.ofNullable(storage.get(orderNo));
    }
}
