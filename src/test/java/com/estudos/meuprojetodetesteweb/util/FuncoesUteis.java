package com.estudos.meuprojetodetesteweb.util;

import com.estudos.meuprojetodetesteweb.dto.CustomerIn;
import com.estudos.meuprojetodetesteweb.repository.CustomerDaoSQLServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FuncoesUteis {

    @Autowired
    private CustomerDaoSQLServer customerDaoSQLServer;

    public void insereCustomerQueDeveSerRetornado() {
        CustomerIn customerIn = new CustomerIn();
        customerIn.setNome("Maria joaquina");
        customerIn.setCep("50123-445");
        customerIn.setEndereco("Av Rui Barbosa, 123");
        customerIn.setCpf("132.123.003-71");
        customerDaoSQLServer.salvar(customerIn);
    }


}
