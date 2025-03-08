package com.estudos.meuprojetodetesteweb.controller;


import com.estudos.meuprojetodetesteweb.dto.CustomerIn;
import com.estudos.meuprojetodetesteweb.dto.CustomerOut;
import com.estudos.meuprojetodetesteweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerOut> salvar(@RequestBody CustomerIn customerIn) {
        CustomerOut customerOut = customerService.salvar(customerIn);

        URI uri = URI.create("localhost:8080/customer/" + customerOut.getCpf());

        return ResponseEntity.created(uri).body(customerOut);

    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerOut> consultarPorCpf(@PathVariable("cpf") String cpf) {
        CustomerOut customerOut = customerService.consultar(cpf);

        return ResponseEntity.ok(customerOut);
    }

}
