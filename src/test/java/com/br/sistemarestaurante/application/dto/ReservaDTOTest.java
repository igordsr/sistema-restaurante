package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.domain.entity.Reserva;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservaDTOTest {

    @Test
    void testToDomainEntity() {
        UUID id = UUID.randomUUID();
        UUID restauranteId = UUID.randomUUID();
        String nomeCliente = "Cliente Teste";
        String emailCliente = "cliente@teste.com";
        String telefoneCliente = "123456789";
        Calendar data = Calendar.getInstance();
        LocalTime hora = LocalTime.of(12, 0);

        ReservaDTO reservaDTO = new ReservaDTO(id, restauranteId, nomeCliente, emailCliente, telefoneCliente, data, hora, null);

        Reserva reserva = reservaDTO.ToDomainEntity();

        assertNotNull(reserva);
        assertEquals(id, reserva.getIdentificador());
        assertEquals(restauranteId, reserva.getRestauranteId());
        assertEquals(nomeCliente, reserva.getCliente().getNome());
        assertEquals(emailCliente, reserva.getCliente().getEmail());
        assertEquals(telefoneCliente, reserva.getCliente().getTelefone());
        assertEquals(data, reserva.getData());
        assertEquals(hora, reserva.getHora());
    }
}
