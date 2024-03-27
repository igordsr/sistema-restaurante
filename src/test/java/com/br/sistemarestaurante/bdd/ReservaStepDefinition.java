package com.br.sistemarestaurante.bdd;

import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ReservaStepDefinition {

    private Response response;

    private Restaurante restaurante;

    private String ENDPOINT_API_RESTAURANTE = "http://localhost:8080/api/restaurante";

    @Quando("Registrar um novo Restaurante")
    public void registrar_um_novo_restaurante() {
        var restauranteRequest = new RestauranteDTO(UUID.randomUUID(), "Nome Teste", "Rua Teste", "Tipo Cozinha Teste", LocalTime.of(14, 8, 2), LocalTime.of(22, 8, 2), 50);
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restauranteRequest)
                .when()
                .post(ENDPOINT_API_RESTAURANTE);
    }

    @Entao("o Restaurante é registrado com sucesso")
    public void o_restaurante_e_registrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Entao("o Restaurante Registrado deve ser apresentado")
    public void o_restaurante_registrado_deve_ser_apresentado() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/restaurante.schema.json"));
    }
}
