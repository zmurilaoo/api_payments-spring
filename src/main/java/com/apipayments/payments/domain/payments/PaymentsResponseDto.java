package com.apipayments.payments.domain.payments;

import com.apipayments.payments.enums.PaymentMehtod;

import java.math.BigDecimal;

public record PaymentsResponseDto( String payNamer, BigDecimal amount,  PaymentMehtod paymentMehtod) {
    public PaymentsResponseDto(Payments payments) {
        this(  payments.getPayNamer(), payments.getAmount(), payments.getPaymentMethod());
    }
}
