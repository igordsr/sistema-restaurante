package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteNotFoundExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        ClienteNotFoundException exception = new ClienteNotFoundException();

        assertEquals("Cliente não cadastrado na base de dados.", exception.getMessage());
    }

    @Test
    void testThrowClienteNotFoundException() {
        assertThrows(ClienteNotFoundException.class, () -> {
            throw new ClienteNotFoundException();
        });
    }
}
