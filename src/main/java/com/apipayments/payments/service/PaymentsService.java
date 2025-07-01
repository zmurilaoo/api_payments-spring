package com.apipayments.payments.service;


import com.apipayments.payments.domain.payments.PaymentsResponseDto;
import com.apipayments.payments.domain.payments.RespostSucess;
import com.apipayments.payments.execeptions.ValidationPayments;
import com.apipayments.payments.domain.payments.Payments;
import com.apipayments.payments.repository.PaymentsRepository;
import com.apipayments.payments.validator.ValidationField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class PaymentsService {

    private final PaymentsRepository repository;
    private final ValidationField validation;


    public void validationPayments(Payments pay) {

        validation.isvalid(pay);

        if (pay.getAmount().compareTo(BigDecimal.ZERO) <= 0.0) {
            throw new ValidationPayments("O valor não pode ser zero! ");
        }


        repository.save(pay);
    }

    public RespostSucess pegar(String id) {
        var idRecebido = UUID.fromString(id);
        var optionalPay = repository.findById(idRecebido);

        if (optionalPay.isEmpty()) {
            throw new ValidationPayments("Pagamento não encotrado");
        }

        Payments pay = optionalPay.get();


        PaymentsResponseDto payDto = new PaymentsResponseDto(pay);

        RespostSucess respost = new RespostSucess("Pagamento Encontrado!", payDto);


        return respost;
    }

    public void delete(String id) {
        var idRecebido = UUID.fromString(id);
        if (!repository.existsById(idRecebido)) {
            throw new ValidationPayments("Id Não encontrado! ");
        }

        repository.deleteById(idRecebido);
    }


}

