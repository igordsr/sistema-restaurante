package com.br.sistemarestaurante.bdd;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class AvaliacaoTest {

    private Response response;
    private String ENDPOINT_API_AVALIACAO = "http://localhost:8080/api/avaliacao";

    @Quando("Registrar uma nova Avaliacao")
    public void registrar_uma_nova_avaliacao() {
        var avaliacaoRequest = new AvaliacaoDTO(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Ótimo sabor",
                4
        );
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(avaliacaoRequest)
                .when()
                .post(ENDPOINT_API_AVALIACAO);
    }

    @Entao("a Avaliacao é registrada com sucesso")
    public void a_avaliacao_e_registrada_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Entao("a Avaliacao Registrada deve ser apresentada")
    public void a_avaliacao_registrada_deve_ser_apresentada() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/avaliacao.schema.json"));
    }
}
