package com.apipayments.payments.domain.payments;


import com.apipayments.payments.enums.PaymentMehtod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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
    private String payNamer;


    @Column(name = "document_payment", nullable = false)
    private String payerDocument;


    @Column(name = "ammount", precision = 18, scale = 2, nullable = false)
    private BigDecimal amount;

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

    public Payments(PaymentsRequestDto payDto) {
        this.payNamer = payDto.payName();
        this.amount = payDto.amount();
        this.paymentMethod = payDto.paymentMethod();
    }


}
