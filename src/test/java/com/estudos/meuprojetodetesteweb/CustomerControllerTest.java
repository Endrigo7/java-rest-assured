package com.estudos.meuprojetodetesteweb;

import com.estudos.meuprojetodetesteweb.model.Customer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @LocalServerPort
    private int portaUsada;

    @BeforeEach
    public void setup(){
        RestAssured.port = portaUsada;
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
    public void salvarCustomerFluxoPrincipal(){
        Customer customer = new Customer();
        customer.setNome("Seu amaro");
        customer.setCpf("222.111.333-14");
        customer.setCep("35121-091");

        RestAssured
                .given()
                    .body(customer)
                    .contentType(ContentType.JSON)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("endereco", equalTo("Rua bla bla bla"));
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
        Customer customer = new Customer();
        customer.setNome("Seu paulo");
        customer.setCpf("222");
        customer.setCep("35121-091");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customer)
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
        Customer customer = new Customer();
        customer.setNome("Maria Joaquina");
        customer.setCpf("123.123.123-12");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customer)
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
        Customer customer = new Customer();
        customer.setCpf("123.123.222-33");
        customer.setCep("32112-123");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customer)
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
        Customer customer = new Customer();
        customer.setNome("Maria joaquina");
        customer.setCep("50123-445");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(customer)
                .when()
                    .request("POST", "/customer")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body(equalTo("CPF deve ser informado"));
    }

}
