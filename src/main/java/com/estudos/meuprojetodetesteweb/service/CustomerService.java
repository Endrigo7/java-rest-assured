package com.estudos.meuprojetodetesteweb.service;

import com.estudos.meuprojetodetesteweb.dto.CustomerIn;
import com.estudos.meuprojetodetesteweb.dto.CustomerOut;
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

    public CustomerOut salvar(CustomerIn customerIn){
        customerValidator.valida(customerIn);

        if(customerIn.getEndereco() == null) {
            String endereco = cepService.consultaEnderecoPorCep(customerIn.getCep());
            customerIn.setEndereco(endereco);
        }


        customerDaoSQLServer.salvar(customerIn);

        CustomerOut customerOut = new CustomerOut();
        customerOut.setCpf(customerIn.getCpf());
        customerOut.setNome(customerIn.getNome());
        customerOut.setCep(customerIn.getCep());
        customerOut.setEndereco(customerIn.getEndereco());
        customerOut.setSalvo(Boolean.TRUE);

        return customerOut;
    }

    public CustomerOut consultar(String cpf){
        CustomerIn customerIn = customerDaoSQLServer.consultar(cpf);

        CustomerOut customerOut = new CustomerOut();
        customerOut.setCpf(customerIn.getCpf());
        customerOut.setNome(customerIn.getNome());
        customerOut.setCep(customerIn.getCep());
        customerOut.setEndereco(customerIn.getEndereco());
        customerOut.setSalvo(Boolean.TRUE);

        return customerOut;
    }

}
