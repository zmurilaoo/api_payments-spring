package com.apipayments.payments.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "pagamento")
@NoArgsConstructor


public class payments {


    @GeneratedValue(strategy = GenerationType.UUID)
    @Size()
    @Column(name = "id", nullable = false)
    private UUID id;


    @Column(name = "nome", nullable = false)
    private String paynamer;


    @Column(name = "documento", nullable = false)
    private String payerDocument;

    @Column(name = "quantia", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento")
    private status paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento")
    private paymentMehtod paymentMehtod;

    @Column(name = "date_")
    private LocalDateTime createAt;


}
