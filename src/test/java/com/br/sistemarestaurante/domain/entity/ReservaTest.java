package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.SystemException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaTest {
    @Test
    public void testValidate_ValidReserva_ShouldNotThrowException() {
        UUID identificador = UUID.randomUUID();
        UUID restauranteId = UUID.randomUUID();
        Cliente cliente = new Cliente(UUID.randomUUID(), "Fulano", "fulano@example.com", "123456789");
        Calendar data = Calendar.getInstance();
        LocalTime hora = LocalTime.now();

        Reserva reserva = new Reserva(identificador, restauranteId, cliente, data, hora);

        assertEquals(reserva, reserva.validate());
    }

    @Test
    public void testValidate_NullRestauranteId_ShouldThrowSystemException() {
        UUID identificador = UUID.randomUUID();
        UUID restauranteId = null;
        Cliente cliente = new Cliente(UUID.randomUUID(), "Fulano", "fulano@example.com", "123456789");
        Calendar data = Calendar.getInstance();
        LocalTime hora = LocalTime.now();

        Reserva reserva = new Reserva(identificador, restauranteId, cliente, data, hora);

        SystemException exception = assertThrows(SystemException.class, reserva::validate);
        assertEquals("[restaurante] não pode ser nulo ou branco", exception.getMessage());
    }
}
