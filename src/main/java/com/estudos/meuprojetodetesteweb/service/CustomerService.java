package com.estudos.meuprojetodetesteweb.service;

import com.estudos.meuprojetodetesteweb.model.Customer;
import com.estudos.meuprojetodetesteweb.repository.CustomerDaoSQLServer;
import com.estudos.meuprojetodetesteweb.resources.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private CustomerDaoSQLServer customerDaoSQLServer;

    @Autowired
    private CepService cepService;

    public Customer salvar(Customer customer){
        customerValidator.valida(customer);

        if(customer.getEndereco() == null) {
            String endereco = cepService.consultaEnderecoPorCep(customer.getCep());
            customer.setEndereco(endereco);
        }

        customer.setSalvo(true);
        customerDaoSQLServer.salvar(customer);

        return customer;
    }

    public Customer consultar(String cpf){
        return customerDaoSQLServer.consultar(cpf);
    }

}
