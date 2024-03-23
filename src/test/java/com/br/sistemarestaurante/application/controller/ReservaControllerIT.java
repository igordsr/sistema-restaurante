package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.ReservaDTO;
import com.br.sistemarestaurante.application.gateway.ReservaGateway;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservaController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ReservaControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReservaGateway reservaGateway;

    ReservaDTO reservaDTO;

    @BeforeEach
    void setUp() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, 1);

        reservaDTO = new ReservaDTO(UUID.randomUUID(), UUID.randomUUID(), "Cliente Nome", "email@cliente.com", "11999999999", date, LocalTime.of(14, 30, 0), StatusReserva.RESERVADO);
    }

    @Test
    void deveRegistrarReservaQuandoReservaDtoValido() throws Exception {
        when(reservaGateway.registrar(any(ReservaDTO.class))).thenReturn(reservaDTO);

        mockMvc.perform(post("/reserva")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void deveAtualizarStatusReserva() throws Exception {
        UUID reservaId = UUID.randomUUID();
        StatusReserva statusReserva = StatusReserva.CONCLUIDO;
        when(reservaGateway.alterarStatus(reservaId, statusReserva)).thenReturn(reservaDTO);

        mockMvc.perform(put("/reserva/{id}/status", reservaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(statusReserva)))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarReservaPorRestaurante() throws Exception {
        UUID restauranteId = UUID.fromString("123e4567-e89b-12d3-a456-426614174002");
        when(reservaGateway.buscarReservaPorRestaurante(restauranteId)).thenReturn(Collections.singletonList(reservaDTO));

        mockMvc.perform(get("/reserva/restaurante/{id}", restauranteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }
}
