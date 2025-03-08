package com.estudos.meuprojetodetesteweb;

import com.estudos.meuprojetodetesteweb.model.Customer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeuProjetoDeTesteWebApplicationTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() throws Exception {
		RestAssured.port = port;
	}

	// Cenário de Sucesso
	/*
	 *	(GIVEN) DADO um cliente com nome e cpf válidos
	 *	(WHEN) QUANDO enviar os dados pro sistema (POST)
	 *	(THEN) ENTÃO o cadastro será realizado com sucesso
	 *	(AND) E retornar o codigo http 201
	 *	(AND) E com header localization com a url de consulta do cliente cadastrado.
	 *	(AND) E retorna o cliente criado no corpo da mensagem
	 */
	@Test
	public void dadoUmClienteComDadosValidosEntaoOClienteDeveSerSalvo(){
		Customer customer = new Customer();
		customer.setCpf("333.123.123-50");
		customer.setNome("Maria Joaquina");

		RestAssured.given()
						.contentType(ContentType.JSON)
						.body(customer)
					.when()
						.request("POST", "customer")
					.then()
						.statusCode(HttpStatus.CREATED.value())
						.header("Location", containsString("customer/333.123.123-50" ))
						.body("cpf", equalTo("333.123.123-50"))
						.body("nome", equalTo("Maria Joaquina"))
						.body("salvo", equalTo(true));

	}


	/*
	 *  Validar falhar ao cadastrar cliente quando informar um cpf invalido
	 *	(GIVEN) DADO que informe todas as informações obrigatórias
	 *	(WHEN) QUANDO submeter minha solicitação via POST
	 *	(BUT) MAS informar como cpf um valor com 13 caracteres
	 *	(THEN) ENTÃO o cadastro deve falhar
	 *	(AND) E retornar o codigo http 400 (BAD REQUEST)
	 *  (AND) E deve retonar a mensagem CPF Invalido no corpo da mensagem
	 * */
	@Test
	public void dadoUmClienteComCamposPreenchidosPoremCpfInvalidoEntaoDeveRetonarErro(){
		Customer customer = new Customer();
		customer.setCpf("333.123.123");
		customer.setNome("Maria Joaquina");

		RestAssured.given()
						.contentType(ContentType.JSON)
						.body(customer)
					.when()
						.request("POST", "/customer")
					.then()
						.statusCode(HttpStatus.BAD_REQUEST.value())
						.body(equalTo("CPF Invalido"));
	}

}
