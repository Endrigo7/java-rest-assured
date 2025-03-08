package com.estudos.meuprojetodetesteweb.repository;

import com.estudos.meuprojetodetesteweb.dto.CustomerIn;
import com.estudos.meuprojetodetesteweb.dto.CustomerOut;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoSQLServer extends CustomerDaoAbstract{

    @Override
    public CustomerIn consultar(String cpf) {
        return customerIns.stream()
                .filter(customer -> customer.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}
