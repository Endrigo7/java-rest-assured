package com.estudos.meuprojetodetesteweb.service;

import com.estudos.meuprojetodetesteweb.dto.CustomerIn;
import com.estudos.meuprojetodetesteweb.exceptions.CamposObrigatoriosNaoPreenchidosException;
import com.estudos.meuprojetodetesteweb.exceptions.CpfInvalidoException;
import com.estudos.meuprojetodetesteweb.dto.CustomerOut;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {


    public void valida(CustomerIn customerIn){
        validarCamposObrigatoriosPreenchidos(customerIn);
        validaFormatoDoCpf(customerIn);
    }

    private void validarCamposObrigatoriosPreenchidos(CustomerIn customerIn) {

        if(customerIn == null) {
            throw new RuntimeException("Customer nao pode ser nulo");
        }

        if(customerIn.getCpf() == null) {
            throw new CamposObrigatoriosNaoPreenchidosException("CPF deve ser informado");
        }

        if(customerIn.getNome() == null) {
            throw new CamposObrigatoriosNaoPreenchidosException("Nome deve ser informado");
        }

        if(customerIn.getCep() == null) {
            throw new CamposObrigatoriosNaoPreenchidosException("CEP deve ser informado");
        }

    }

    private void validaFormatoDoCpf(CustomerIn customerIn) {
        if(customerIn.getCpf().length() != 14) {
            throw new CpfInvalidoException("CPF Invalido");
        }
    }

}
