package com.apipayments.payments.api.common;


import com.apipayments.payments.execeptions.ValidationPayments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//Objetivo capturar execeptions
// e retorna um resposta rest

//@Exeception

@RestControllerAdvice
public class GlobalExeceptionHandler {

    ///Utilizamos isso para deixar o código mais limpo...

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<String >HandleException(ValidationPayments e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }



}
