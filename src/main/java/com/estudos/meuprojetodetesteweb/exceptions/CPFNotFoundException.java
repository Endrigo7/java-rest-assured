package com.estudos.meuprojetodetesteweb.exceptions;

public class CPFNotFoundException extends RuntimeException{

    public CPFNotFoundException(String mensagem) {
        super(mensagem);
    }

}
