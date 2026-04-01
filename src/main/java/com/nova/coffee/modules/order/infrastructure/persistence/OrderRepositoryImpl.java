package com.nova.coffee.modules.order.infrastructure.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nova.coffee.modules.order.domain.model.CoffeeOrder;
import com.nova.coffee.modules.order.domain.model.OrderStatus;
import com.nova.coffee.modules.order.domain.repository.OrderRepository;
import com.nova.coffee.modules.order.infrastructure.persistence.dataobject.CoffeeOrderEntity;
import com.nova.coffee.modules.order.infrastructure.persistence.mapper.CoffeeOrderMapper;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 订单仓储实现。
 *
 * <p>优先落 MySQL；如果数据库尚未准备好，则回退到内存模式，保证学习链路不中断。
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final CoffeeOrderMapper coffeeOrderMapper;
    private final Map<String, CoffeeOrder> fallbackStorage = new ConcurrentHashMap<>();

    public OrderRepositoryImpl(CoffeeOrderMapper coffeeOrderMapper) {
        this.coffeeOrderMapper = coffeeOrderMapper;
    }

    @Override
    public void save(CoffeeOrder order) {
        try {
            CoffeeOrderEntity entity = toEntity(order);
            CoffeeOrderEntity existing = coffeeOrderMapper.selectOne(
                new LambdaQueryWrapper<CoffeeOrderEntity>()
                    .eq(CoffeeOrderEntity::getOrderNo, order.getOrderNo())
                    .last("limit 1")
            );
            if (existing == null) {
                coffeeOrderMapper.insert(entity);
            } else {
                entity.setId(existing.getId());
                coffeeOrderMapper.updateById(entity);
            }
            return;
        } catch (Exception exception) {
            fallbackStorage.put(order.getOrderNo(), order);
        }
    }

    @Override
    public Optional<CoffeeOrder> findByOrderNo(String orderNo) {
        try {
            CoffeeOrderEntity entity = coffeeOrderMapper.selectOne(
                new LambdaQueryWrapper<CoffeeOrderEntity>()
                    .eq(CoffeeOrderEntity::getOrderNo, orderNo)
                    .last("limit 1")
            );
            if (entity != null) {
                return Optional.of(toDomain(entity));
            }
        } catch (Exception exception) {
            return Optional.ofNullable(fallbackStorage.get(orderNo));
        }
        return Optional.ofNullable(fallbackStorage.get(orderNo));
    }

    private CoffeeOrderEntity toEntity(CoffeeOrder order) {
        CoffeeOrderEntity entity = new CoffeeOrderEntity();
        entity.setOrderNo(order.getOrderNo());
        entity.setTenantId(order.getTenantId());
        entity.setProductCode(order.getProductCode());
        entity.setProductName(order.getProductName());
        entity.setQuantity(order.getQuantity());
        entity.setTotalAmount(order.getTotalAmount());
        entity.setStatus(order.getStatus().name());
        entity.setCreatedTime(order.getCreatedTime());
        return entity;
    }

    private CoffeeOrder toDomain(CoffeeOrderEntity entity) {
        return CoffeeOrder.rebuild(
            entity.getOrderNo(),
            entity.getTenantId(),
            entity.getProductCode(),
            entity.getProductName(),
            entity.getQuantity(),
            entity.getTotalAmount(),
            entity.getCreatedTime(),
            OrderStatus.valueOf(entity.getStatus())
        );
    }
}
