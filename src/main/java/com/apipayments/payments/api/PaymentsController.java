package com.apipayments.payments.api;

import com.apipayments.payments.domain.payments.PaymentsResponseDto;
import com.apipayments.payments.domain.payments.RespostSucess;
import com.apipayments.payments.domain.payments.Payments;
import com.apipayments.payments.repository.PaymentsRepository;
import com.apipayments.payments.service.PaymentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor

///Adicionar Validador de duplicações tanto de nome

public class PaymentsController {

    private final PaymentsRepository repository;

    private final PaymentsService service;


    @PostMapping
    public ResponseEntity<RespostSucess>createPayment(@RequestBody @Valid Payments pay) {

            service.validationPayments(pay);

            PaymentsResponseDto payDto =  new PaymentsResponseDto(pay);

            RespostSucess respost = new RespostSucess("Pagamento Criado", payDto);
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(pay.getId()).toUri();

            return ResponseEntity.ok(respost);

    }

    @GetMapping({"/{id}"})
    public ResponseEntity<RespostSucess> getPagamento(@PathVariable("id") @Valid String id ){
        var trazer = service.pegar(id);
        return ResponseEntity.ok(trazer);

    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deletePayments(@PathVariable("id") @Valid String id) {
            service.delete(id);
            return ResponseEntity.ok().build();
    }

//    @PutMapping({"{id}"})
//    public ResponseEntity<PaymentsResponseDto>  update (@PathVariable("id") @Valid  String id, @RequestBody PaymentsRequestDto paydto) {
//        try {
//            service.update(id, paydto);
//            PaymentsResponseDto responseDto = new PaymentsResponseDto("Pagamento Atualizado", paydto);
//            return ResponseEntity.ok(responseDto);
//        } catch (ValidationPayments e) {
//           return ResponseEntity.badRequest().body(new PaymentsResponseDto("Pagamento não encontrado", paydto));
//        }
//    }

}
