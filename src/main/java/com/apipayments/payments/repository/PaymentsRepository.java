package com.apipayments.payments.repository;

import com.apipayments.payments.dto.PaymentsDto;
import com.apipayments.payments.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, UUID> {


}
