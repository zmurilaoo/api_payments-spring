package com.apipayments.payments.repository;

import com.apipayments.payments.domain.payments.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {

    UserDetails findByUser(String people);
}
