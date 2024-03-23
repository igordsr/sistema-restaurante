package com.br.sistemarestaurante.bdd.integration;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrarAvaliacaoSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private AvaliacaoDTO avaliacaoEnviada;
    private ResponseEntity<AvaliacaoDTO> resposta;

    @Given("Eu tenho uma avaliação válida")
    public void eu_tenho_uma_avaliacao_valida() {
        avaliacaoEnviada = new AvaliacaoDTO(null, UUID.randomUUID(), "Comentário de teste", 5);
    }

    @When("Eu registro a avaliação")
    public void eu_registro_a_avaliacao() {
        resposta = restTemplate.postForEntity("/avaliacao", avaliacaoEnviada, AvaliacaoDTO.class);
    }

    @Then("A avaliação registrada deve ter os mesmos dados da avaliação enviada")
    public void a_avaliacao_registrada_deve_ter_os_mesmos_dados_da_avaliacao_enviada() {
        AvaliacaoDTO avaliacaoRegistrada = resposta.getBody();
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(avaliacaoEnviada.reservaId(), avaliacaoRegistrada.reservaId());
        assertEquals(avaliacaoEnviada.comentario(), avaliacaoRegistrada.comentario());
        assertEquals(avaliacaoEnviada.classificacao(), avaliacaoRegistrada.classificacao());
    }
}