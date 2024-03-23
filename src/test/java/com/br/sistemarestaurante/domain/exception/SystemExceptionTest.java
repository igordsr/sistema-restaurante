package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemExceptionTest {
    @Test
    void testConstructorAndGetMessage() {
        String errorMessage = "Erro genérico";

        SystemException exception = new SystemException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
