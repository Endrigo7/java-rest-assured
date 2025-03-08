package com.estudos.meuprojetodetesteweb.exceptions;

public class CamposObrigatoriosNaoPreenchidosException extends RuntimeException{

    public CamposObrigatoriosNaoPreenchidosException(String mensagem) {
        super(mensagem);
    }
}
