package com.nova.coffee.modules.order.application;

import com.nova.coffee.infrastructure.mq.MqProducer;
import com.nova.coffee.modules.order.domain.model.CoffeeOrder;
import java.util.Map;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 订单异步通知服务。
 */
@Service
public class OrderAsyncNotifier {

    private final MqProducer mqProducer;

    public OrderAsyncNotifier(MqProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

    @Async("businessTaskExecutor")
    public void notifyOrderPaid(CoffeeOrder order) {
        mqProducer.send("coffee.order.paid", Map.of(
            "orderNo", order.getOrderNo(),
            "tenantId", order.getTenantId(),
            "productCode", order.getProductCode(),
            "amount", order.getTotalAmount(),
            "status", order.getStatus().name()
        ));
    }
}
