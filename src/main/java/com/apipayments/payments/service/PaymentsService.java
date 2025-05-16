package com.apipayments.payments.service;


import com.apipayments.payments.dto.PaymentsDto;
import com.apipayments.payments.dto.ResponseDto;
import com.apipayments.payments.execeptions.ValidationPayments;
import com.apipayments.payments.model.Payments;
import com.apipayments.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class PaymentsService {

    private final PaymentsRepository repository;



    public ResponseEntity<ResponseDto> validationPayments(Payments pay) {

            if (pay.getAmount().compareTo(BigDecimal.ZERO) <= 0.0) {
                throw new ValidationPayments("O valor não pode ser zero! ");
            }

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(pay.getId()).toUri();


            repository.save(pay);

            return ResponseEntity.created(location).build();

    }

    public ResponseDto pegar(String id) {
        var idRecebido = UUID.fromString(id);
        var optionalPay = repository.findById(idRecebido);

        if (optionalPay.isEmpty()) {
            throw new ValidationPayments("Pagamento não encotrado");
        }

        Payments pay = optionalPay.get();
        PaymentsDto dto = new PaymentsDto(pay.getPayNamer(), pay.getPayerDocument(), pay.getAmount(), pay.getPaymentMethod());
        return new ResponseDto("Pagamento encontrado!", dto);
    }

    public void delete(String id) {
        var idRecebido = UUID.fromString(id);
        if (!repository.existsById(idRecebido)) {
            throw new ValidationPayments("Id Não encontrado! ");
        }

        repository.deleteById(idRecebido);
    }

    public void update(String id, PaymentsDto paydto) {
        var payId = UUID.fromString(id);

        Optional<Payments> alterar = repository.findById(payId);

        if (alterar.isEmpty()) {
            throw new ValidationPayments("Campo não pode ser vazio!");
        }

        if (alterar.isPresent()) {
            Payments pay =  new Payments();
            pay.setPayNamer(paydto.payNamer());
            pay.setPayerDocument(paydto.payerDocument());

            repository.save(pay);

        }

    }





}
