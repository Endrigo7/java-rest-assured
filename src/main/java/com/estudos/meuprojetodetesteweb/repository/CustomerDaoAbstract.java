package com.estudos.meuprojetodetesteweb.repository;

import com.estudos.meuprojetodetesteweb.dto.CustomerIn;
import com.estudos.meuprojetodetesteweb.dto.CustomerOut;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomerDaoAbstract {

    protected final List<CustomerIn> customerIns = new ArrayList<>();

    public void salvar(CustomerIn customerIn) {
        customerIns.add(customerIn);
    }

    public abstract CustomerIn consultar(String cpf);

}
