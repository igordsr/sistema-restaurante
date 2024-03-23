package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.domain.entity.StatusReserva;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaAlterarStatusDTOTest {

    @Test
    void testConstructor() {
        UUID reservaId = UUID.randomUUID();
        StatusReserva statusReserva = StatusReserva.CONCLUIDO;

        ReservaAlterarStatusDTO dto = new ReservaAlterarStatusDTO(reservaId, statusReserva);

        assertEquals(reservaId, dto.reservaId());
        assertEquals(statusReserva, dto.statusReserva());
    }
}
