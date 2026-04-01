package com.nova.coffee.modules.payment.domain.repository;

import com.nova.coffee.modules.payment.domain.model.PaymentRecord;
import java.util.Optional;

/**
 * 支付仓储接口。
 */
public interface PaymentRepository {

    Optional<PaymentRecord> findLatestByOrderNo(String orderNo);
}
