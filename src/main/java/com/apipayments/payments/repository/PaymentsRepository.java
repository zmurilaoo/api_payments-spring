package com.apipayments.payments.repository;

import com.apipayments.payments.domain.payments.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, UUID> {

//   boolean existsByPayNamerAndPayerDocument(String payNamer, String payerDocument);

   Optional<Payments> findByPayNamerAndPayerDocument(String payNamer, String payerDocument);
}
