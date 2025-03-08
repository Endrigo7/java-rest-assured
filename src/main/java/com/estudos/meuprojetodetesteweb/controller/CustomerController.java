package com.estudos.meuprojetodetesteweb.controller;


import com.estudos.meuprojetodetesteweb.model.Customer;
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
    public ResponseEntity<Customer> salvar(@RequestBody Customer customer) {
        customerService.salvar(customer);

        URI uri = URI.create("localhost:8080/customer/" + customer.getCpf());

        return ResponseEntity.created(uri).body(customer);

    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Customer> consultarPorCpf(@PathVariable("cpf") String cpf) {
        Customer customer = customerService.consultar(cpf);

        return ResponseEntity.ok(customer);
    }

}
