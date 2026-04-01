package com.nova.coffee.modules.payment.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.payment.application.PaymentApplicationService;
import com.nova.coffee.modules.payment.interfaces.dto.PaymentRecordResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付接口。
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentApplicationService paymentApplicationService;

    public PaymentController(PaymentApplicationService paymentApplicationService) {
        this.paymentApplicationService = paymentApplicationService;
    }

    @GetMapping("/orders/{orderNo}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<PaymentRecordResponse> latestPayment(@PathVariable String orderNo) {
        return Result.success(paymentApplicationService.latestPayment(orderNo));
    }
}
