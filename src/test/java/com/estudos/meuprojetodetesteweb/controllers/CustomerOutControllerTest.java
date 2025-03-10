package com.estudos.meuprojetodetesteweb.controllers;

import com.estudos.meuprojetodetesteweb.dto.CustomerIn;
import com.estudos.meuprojetodetesteweb.util.FuncoesUteis;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerOutControllerTest {

    @Autowired
    private FuncoesUteis funcoesUteis;

    @LocalServerPort
    private int portaUsada;

    @BeforeEach
    public void setup(){
        RestAssured.port = portaUsada;
    }

    /*
     * DADO nome, CPF, CEP e Endereco preenchidos
     * QUANDO chamar endpoint customer(POST)
     * ENTAO Salvar o cliente
     * E retornar o status 201
     */
    @Test
    public void salvarCustomerFluxoPrincipal(){
        CustomerIn customerIn = new CustomerIn();
        customerIn.setNome("Maria Joaquina de Amaral Pereira Goes");
        customerIn.setCpf("872.234.532-23");
        customerIn.setCep("52123-012");
        customerIn.setEndereco("Av Boa viagem, 123");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customerIn)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("cpf", equalTo("872.234.532-23"))
                    .body("nome", equalTo("Maria Joaquina de Amaral Pereira Goes"))
                    .body("cep", equalTo("52123-012"))
                    .body("endereco", equalTo("Av Boa viagem, 123"))
                    .body("salvo", equalTo(Boolean.TRUE));

    }

    /*
     * DADO nome, CPF e CEP preenchidos
     * QUANDO chamar endpoint customer(POST)
     * ENTAO deve consultar o CEP
     * E adicionar ao endereco o resultado
     * E Salvar o cliente
     * E retornar o status 201
     */
    @Test
    public void salvarCustomerFluxoPrincipalSemEnderecoPreenchido(){
        CustomerIn customerIn = new CustomerIn();
        customerIn.setNome("Seu amaro");
        customerIn.setCpf("222.111.333-14");
        customerIn.setCep("35121-091");

        RestAssured
                .given()
                    .body(customerIn)
                    .contentType(ContentType.JSON)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("cpf", equalTo("222.111.333-14"))
                    .body("nome", equalTo("Seu amaro"))
                    .body("cep", equalTo("35121-091"))
                    .body("endereco", equalTo("Rua bla bla bla"))
                    .body("salvo", equalTo(Boolean.TRUE));
    }

    /*
     * DADO nome, CPF e CEP preenchidos
     * E CPF com menos de 14 caracteres
     * QUANDO chamar endpoint customer(POST)
     * ENTAO deve retornar “CPF Invalido”
     * E retornar o status 400
     */
    @Test
    public void salvarCustomerFluxoCPfDiferente14Caracteres(){
        CustomerIn customerIn = new CustomerIn();
        customerIn.setNome("Seu paulo");
        customerIn.setCpf("222");
        customerIn.setCep("35121-091");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customerIn)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(equalTo("CPF Invalido"));
    }

    /*
     * DADO nome, CPF preenchidos
     * QUANDO chamar endpoint customer(POST)
     * ENTAO deve retornar “CEP deve ser informado”
     * E retornar o status 400
     */
    @Test
    public void salvarCustomerFluxoCEPNaoPreenchido(){
        CustomerIn customerIn = new CustomerIn();
        customerIn.setNome("Maria Joaquina");
        customerIn.setCpf("123.123.123-12");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customerIn)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(equalTo("CEP deve ser informado"));
    }

    /*
     * DADO CEP, CPF preenchidos
     * QUANDO chamar endpoint customer(POST)
     * ENTAO deve retornar “Nome deve ser informado”
     * E retornar o status 400
     */
    @Test
    public void salvarCustomerFluxoNomeNaoPreenchido(){
        CustomerIn customerIn = new CustomerIn();
        customerIn.setCpf("123.123.222-33");
        customerIn.setCep("32112-123");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customerIn)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(equalTo("Nome deve ser informado"));
    }

    /*
     * DADO NOME, CEP preenchidos
     * QUANDO chamar endpoint customer(POST)
     * ENTAO deve retornar “CPF deve ser informado”
     * E retornar o status 400
     */
    @Test
    public void salvarCustomerFluxoCpfNaoPreenchido(){
        CustomerIn customerIn = new CustomerIn();
        customerIn.setNome("Maria joaquina");
        customerIn.setCep("50123-445");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customerIn)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(equalTo("CPF deve ser informado"));
    }

    /*
     * DADO que o CPF preenchido existe na banco de dados
     * QUANDO chamar o endpoint customer/{CPF} (GET)
     * ENTAO deve retornar as informações do CUSTOMER
     * E status 200.
     */
    @Test
    public void consultaCustomerPorCPFFluxoPrincipal(){
        funcoesUteis.insereCustomerQueDeveSerRetornado();

        RestAssured
                .given()
                .when()
                    .request("GET", "/customer/132.123.003-71")
                .then()
                    .statusCode(HttpStatus.OK.value())
                .body("cpf", equalTo("132.123.003-71"))
                .body("nome", equalTo("Maria joaquina"))
                .body("cep", equalTo("50123-445"))
                .body("endereco", equalTo("Av Rui Barbosa, 123"))
                .body("salvo", equalTo(Boolean.TRUE));

    }

    /*
     * DADO que o CPF preenchido NÃO existe no banco de dados
     * QUANDO chamar o endpoint customer/{CPF} (GET)
     * ENTAO deve retornar status 404
     * E mensagem “O cpf {CPF} não foi encontrado no banco de dados!!!!”.
     */
    @Test
    public void consultaCustomerFluxoCPFNaoEncontrado(){

        RestAssured
                .given()
                .when()
                    .request("GET", "/customer/123.123.000-00")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body(equalTo("O cpf 123.123.000-00 não foi encontrado no banco de dados!!!!"));

    }

}
