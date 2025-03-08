package com.estudos.meuprojetodetesteweb.controller;


import com.estudos.meuprojetodetesteweb.exceptions.CamposObrigatoriosNaoPreenchidosException;
import com.estudos.meuprojetodetesteweb.exceptions.CpfInvalidoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<Object> trataErroCpfInvalid(Exception ex, WebRequest request){
        return new ResponseEntity<Object>(
                "CPF Invalido", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CamposObrigatoriosNaoPreenchidosException.class)
    public ResponseEntity<Object> trataErroCamposObrigatoriosNaoPreenchidos(Exception ex, WebRequest request){
        return new ResponseEntity<Object>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
