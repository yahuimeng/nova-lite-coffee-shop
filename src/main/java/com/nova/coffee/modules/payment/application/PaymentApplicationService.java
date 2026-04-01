package com.nova.coffee.modules.payment.application;

import com.nova.coffee.common.exception.BizException;
import com.nova.coffee.modules.payment.domain.repository.PaymentRepository;
import com.nova.coffee.modules.payment.interfaces.dto.PaymentRecordResponse;
import org.springframework.stereotype.Service;

/**
 * 支付应用服务。
 */
@Service
public class PaymentApplicationService {

    private final PaymentRepository paymentRepository;

    public PaymentApplicationService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentRecordResponse latestPayment(String orderNo) {
        return paymentRepository.findLatestByOrderNo(orderNo)
            .map(payment -> new PaymentRecordResponse(
                payment.paymentNo(),
                payment.orderNo(),
                payment.amount(),
                payment.channel(),
                payment.status()
            ))
            .orElseThrow(() -> new BizException("支付记录不存在"));
    }
}
