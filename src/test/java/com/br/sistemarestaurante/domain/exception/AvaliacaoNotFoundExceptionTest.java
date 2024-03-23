package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AvaliacaoNotFoundExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        AvaliacaoNotFoundException exception = new AvaliacaoNotFoundException();

        assertEquals("Cliente não cadastrado na base de dados.", exception.getMessage());
    }

    @Test
    void testThrowAvaliacaoNotFoundException() {
        assertThrows(AvaliacaoNotFoundException.class, () -> {
            throw new AvaliacaoNotFoundException();
        });
    }
}
