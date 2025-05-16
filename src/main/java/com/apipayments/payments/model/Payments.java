package com.apipayments.payments.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "pagamento")
@NoArgsConstructor


public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;


    @Column(name = "name_payment", nullable = false)
    @Size(max = 30, message = "O nome só pode conter 30 caracters.")
    @NotNull
    private String payNamer;


    @Size(max = 20, message = "O documento só pode conter 20 caracters.")
    @Column(name = "document_payment", nullable = false)
    @NotBlank
    private String payerDocument;


    @Column(name = "ammount", precision = 18, scale = 2, nullable = false)
    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_payment")
    private StatusPayment statusPayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "method_payment")
    private PaymentMehtod paymentMethod;

    @Column(name = "create_at")
    private LocalDateTime createAt;


    //Auditoria
    @CreationTimestamp //para pegar a hora de criação do pagamento.
    @Column(name = "data_created")
    private LocalDateTime dataCreated;

    @LastModifiedDate
    @Column(name = "date_update")
    private LocalDateTime dataUpdate;


}
