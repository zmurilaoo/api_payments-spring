package com.apipayments.payments.validator;

import com.apipayments.payments.execeptions.ValidationPayments;
import com.apipayments.payments.model.Payments;
import com.apipayments.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpPlus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidationField {

    private final PaymentsRepository repository;

    public void isvalid(Payments pay) {
        if (validDuplication(pay)) {
            throw new ValidationPayments("Já existe um pagamento com esse nome e documento!");
        }

    }


    public boolean validDuplication(Payments pay) {

        Optional<Payments> existing = repository.findByPayNamerPayerDocument(pay.getPayNamer(), pay.getPayerDocument());

        if (existing == null) {
            existing.isPresent();
        }


        if (existing.isPresent() && !existing.get().getId().equals(pay.getId())) {
            throw new ValidationPayments("Já existe um pagamento com esse nome e documento!");
        }


        return false;

    }

}
