package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteNotFoundExceptionTest {
    @Test
    void testConstructorAndGetMessage() {
        RestauranteNotFoundException exception = new RestauranteNotFoundException();

        assertEquals("Restaurante não cadastrado na base de dados.", exception.getMessage());
    }

    @Test
    void testThrowRestauranteNotFoundException() {
        assertThrows(RestauranteNotFoundException.class, () -> {
            throw new RestauranteNotFoundException();
        });
    }
}
