package com.br.sistemarestaurante.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.time.LocalTime;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    LocalTime horarioAbertura = LocalTime.of(19, 0); // 19:00
    LocalTime horarioFechamento = LocalTime.of(23, 0); // 23:00

    String requestBody = String.format("{ \"nome\": \"teste nome\", \"localizacao\": \"demo\", \"tipoCozinha\": \"demo\", \"horarioAbertura\": \"%s\", \"horarioFechamento\": \"%s\", \"capacidade\": 5}",
            horarioAbertura.toString(), horarioFechamento.toString());

    ActionBuilder registrarRestauranteRequest = http("registrar restaurante")
            .post("/restaurante")
            .body(StringBody(requestBody))
            .check(status().is(201))
            .check(jsonPath("$.id").saveAs("id"));

    ActionBuilder buscarRestauranteRequest = http("buscar restaurantes")
            .get("/restaurante/restaurantes")
            .check(status().is(200));


    ScenarioBuilder cenarioRegistrarRestaurante = scenario("registrar restaurante")
            .exec(registrarRestauranteRequest);

    ScenarioBuilder cenarioBuscarRestaurantes = scenario("buscar restaurantes")
            .exec(buscarRestauranteRequest);

    {
        setUp(
                cenarioRegistrarRestaurante.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))),

                cenarioBuscarRestaurantes.injectOpen(
                        rampUsersPerSec(1)
                                .to(30)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(30)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(30)
                                .to(1)
                                .during(Duration.ofSeconds(10))))
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(50),
                        global().failedRequests().count().is(0L));
    }
}
