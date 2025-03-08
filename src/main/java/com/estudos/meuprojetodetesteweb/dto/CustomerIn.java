package com.estudos.meuprojetodetesteweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerIn {
    private String cpf;
    private String nome;
    private String cep;
    private String endereco;
}
