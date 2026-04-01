package com.nova.coffee.modules.payment.infrastructure.persistence;

import com.nova.coffee.modules.payment.domain.model.PaymentRecord;
import com.nova.coffee.modules.payment.domain.repository.PaymentRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * 内存版支付仓储。
 */
@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

    @Override
    public Optional<PaymentRecord> findLatestByOrderNo(String orderNo) {
        return Optional.of(new PaymentRecord("PAY-20260401-0001", orderNo, new BigDecimal("56.00"), "MOCK_WECHAT", "SUCCESS"));
    }
}
