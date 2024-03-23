package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaNotFoundExceptionTest {
    @Test
    void testConstructorAndGetMessage() {
        ReservaNotFoundException exception = new ReservaNotFoundException();

        assertEquals("Reserva não cadastrado na base de dados.", exception.getMessage());
    }

    @Test
    void testThrowReservaNotFoundException() {
        assertThrows(ReservaNotFoundException.class, () -> {
            throw new ReservaNotFoundException();
        });
    }
}
