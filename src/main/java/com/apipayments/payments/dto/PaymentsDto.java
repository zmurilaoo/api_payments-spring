package com.apipayments.payments.dto;

import com.apipayments.payments.enums.PaymentMehtod;
import com.apipayments.payments.model.Payments;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentsDto(@NotNull(message = "Campo Obrigatório") String payNamer,
                          @NotBlank(message = "Campo Obrigatório") String payerDocument,
                          @NotNull(message = "Campo Obrigatório") BigDecimal amount,
                          PaymentMehtod paymentMethod) {


    public Payments mapearPayment(PaymentsDto paymentsDto) {
        Payments pay =  new Payments();
        pay.setPayNamer(paymentsDto.payNamer());
        pay.setPayerDocument(paymentsDto.payerDocument);
        pay.setAmount(paymentsDto.amount);
        pay.setPaymentMethod(paymentsDto.paymentMethod);

        return pay;
    }
}


