package com.apipayments.payments.api;

import com.apipayments.payments.dto.PaymentsDto;
import com.apipayments.payments.dto.ResponseDto;
import com.apipayments.payments.execeptions.ValidationPayments;
import com.apipayments.payments.model.Payments;
import com.apipayments.payments.repository.PaymentsRepository;
import com.apipayments.payments.service.PaymentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor

///Adicionar Validador de duplicações tanto de nome



public class PaymentsController {

    private final PaymentsRepository repository;

    private final PaymentsService service;

    @PostMapping
    public ResponseEntity<Object>createPayment(@RequestBody @Valid PaymentsDto payDto) {
        try {

            Payments payments = payDto.mapearPayment(payDto);
            service.validationPayments(payments);


            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(payments.getId()).toUri();

            ResponseDto respostaDto = new ResponseDto("Pagamento Criado! ", payDto);

            return ResponseEntity.ok(respostaDto);

        }catch (RuntimeException e) {
            return  ResponseEntity.badRequest().body(new ResponseDto("Já existe um pagamento com esse nome e documento!", payDto));
        }

    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ResponseDto> getPagamento(@PathVariable("id") @Valid String id ){
        var trazer = service.pegar(id);
        return ResponseEntity.ok(trazer);

    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deletePayments(@PathVariable("id") @Valid String id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (ValidationPayments e ) {
            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping({"{id}"})
    public ResponseEntity<ResponseDto>  update (@PathVariable("id") String id, @RequestBody PaymentsDto paydto) {
        try {
            service.update(id, paydto);
            ResponseDto responseDto = new ResponseDto("Pagamento Atualizado", paydto);
            return ResponseEntity.ok(responseDto);
        } catch (ValidationPayments e) {
           return ResponseEntity.badRequest().body(new ResponseDto("Pagamento não encontrado", paydto));
        }
    }

}
