package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataPassadaExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        DataPassadaException exception = new DataPassadaException();

        assertEquals("Data inválida. A data não pode ser anterior à data atual.", exception.getMessage());
    }

    @Test
    void testThrowDataNaoPassadaException() {
        assertThrows(DataPassadaException.class, () -> {
            throw new DataPassadaException();
        });
    }
}
