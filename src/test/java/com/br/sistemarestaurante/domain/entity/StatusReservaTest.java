package com.br.sistemarestaurante.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusReservaTest {
    @Test
    public void testStatusReservaDescricao() {
        StatusReserva statusReserva = StatusReserva.RESERVADO;

        String descricao = statusReserva.getDescricao();

        assertEquals("reservado", descricao);
    }
}
