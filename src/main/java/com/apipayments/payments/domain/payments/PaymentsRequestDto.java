package com.apipayments.payments.domain.payments;

import com.apipayments.payments.enums.PaymentMehtod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/// Aqui recebe os atributos importantes.
public record PaymentsRequestDto(


        @NotBlank
        String payName,


        @NotNull
        BigDecimal amount,

        @NotEmpty
        PaymentMehtod paymentMethod
)
{

}


