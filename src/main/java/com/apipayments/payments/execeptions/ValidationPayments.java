package com.apipayments.payments.execeptions;

public class ValidationPayments extends RuntimeException {
    public ValidationPayments(String message) {
        super(message);
    }
}
