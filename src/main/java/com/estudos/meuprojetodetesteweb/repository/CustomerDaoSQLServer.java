package com.estudos.meuprojetodetesteweb.repository;

import com.estudos.meuprojetodetesteweb.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoSQLServer extends CustomerDaoAbstract{

    @Override
    public Customer consultar(String cpf) {
        return customers.stream()
                .filter(customer -> customer.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}
