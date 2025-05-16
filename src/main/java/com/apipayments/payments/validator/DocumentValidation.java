package com.apipayments.payments.validator;

import com.apipayments.payments.dto.PaymentsDto;
import com.apipayments.payments.execeptions.ValidationPayments;
import com.apipayments.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;


@RequiredArgsConstructor
public class DocumentValidation {

    private final PaymentsRepository repository;




    public void validationDocument(String document, PaymentsDto payDto) {

        if (document.isEmpty()) {
            throw new ValidationPayments("Esse campo é obrigatório");
        }

        if (repository.existsByPayerDocument(document)) {
            throw new ValidationPayments("Já existe um usuário com esse documento");
        }


    }



}
