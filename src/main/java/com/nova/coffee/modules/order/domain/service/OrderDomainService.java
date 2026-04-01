package com.nova.coffee.modules.order.domain.service;

import com.nova.coffee.modules.order.domain.model.CoffeeOrder;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 * 订单领域服务。
 */
@Service
public class OrderDomainService {

    public CoffeeOrder createOrder(String tenantId, String productCode, String productName, int quantity, BigDecimal unitPrice) {
        return CoffeeOrder.create(tenantId, productCode, productName, quantity, unitPrice);
    }
}
