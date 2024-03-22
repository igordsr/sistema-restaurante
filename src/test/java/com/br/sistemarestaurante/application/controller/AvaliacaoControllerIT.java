package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import com.br.sistemarestaurante.application.gateway.AvaliacaoGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(AvaliacaoController.class)
public class AvaliacaoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AvaliacaoGateway avaliacaoGateway;

    @Test
    void deveRegistrarAvaliacao() throws Exception {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(UUID.randomUUID(), UUID.randomUUID(), "comentário teste", 5);

        when(avaliacaoGateway.registrar(any(AvaliacaoDTO.class))).thenReturn(avaliacaoDTO);

        mockMvc.perform(post("/avaliacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avaliacaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.comentario").value(avaliacaoDTO.comentario())
                );
    }
}
