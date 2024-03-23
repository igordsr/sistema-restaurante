package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaNaoDisponivelExceptionTest {
    @Test
    void testConstructorAndGetMessage() {
        ReservaNaoDisponivelException exception = new ReservaNaoDisponivelException();

        assertEquals("O restaurante não tem disponibilidade para a data e hora selecionadas", exception.getMessage());
    }

    @Test
    void testThrowReservaNaoDisponivelException() {
        assertThrows(ReservaNaoDisponivelException.class, () -> {
            throw new ReservaNaoDisponivelException();
        });
    }
}
