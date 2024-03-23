package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataNaoPassadaExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        DataNaoPassadaException exception = new DataNaoPassadaException();

        assertEquals("Data inválida. A data não pode ser anterior à data atual.", exception.getMessage());
    }

    @Test
    void testThrowDataNaoPassadaException() {
        assertThrows(DataNaoPassadaException.class, () -> {
            throw new DataNaoPassadaException();
        });
    }
}
