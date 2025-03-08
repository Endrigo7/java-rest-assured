package com.estudos.meuprojetodetesteweb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String cpf;
    private String nome;
    private Boolean salvo;
    private String cep;
    private String endereco;


}
