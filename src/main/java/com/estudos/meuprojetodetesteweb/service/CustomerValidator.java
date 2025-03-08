package com.estudos.meuprojetodetesteweb.service;

import com.estudos.meuprojetodetesteweb.exceptions.CpfInvalidoException;
import com.estudos.meuprojetodetesteweb.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {


    public void valida(Customer customer){
        validarCamposObrigatoriosPreenchidos(customer);
        validaFormatoDoCpf(customer);
    }

    private void validarCamposObrigatoriosPreenchidos(Customer customer) {

        if(customer == null) {
            throw new RuntimeException("Customer nao pode ser nulo");
        }

        if(customer.getCpf() == null) {
            throw new RuntimeException("CPF nao pode ser nulo");
        }

        if(customer.getNome() == null) {
            throw new RuntimeException("Nome nao pode ser nulo");
        }

    }

    private void validaFormatoDoCpf(Customer customer) {
        if(customer.getCpf().length() != 14) {
            throw new CpfInvalidoException("CPF Invalido");
        }
    }

}
