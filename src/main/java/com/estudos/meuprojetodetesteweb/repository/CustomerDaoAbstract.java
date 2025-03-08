package com.estudos.meuprojetodetesteweb.repository;

import com.estudos.meuprojetodetesteweb.model.Customer;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomerDaoAbstract {

    protected final List<Customer> customers = new ArrayList<>();

    public void salvar(Customer customer) {
        customers.add(customer);
    }

    public abstract Customer consultar(String cpf);

}
