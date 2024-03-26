package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorarioNoPassadoExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        String hora = "12:00";

        HorarioNoPassadoException exception = new HorarioNoPassadoException(LocalTime.of(12, 00));

        String expectedMessage = String.format("Não é possível agendar um horário no passado [%s]. Por favor, escolha uma data e hora futuras para sua reserva.", hora);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testThrowHorarioNoPassadoException() {
        assertThrows(HorarioNoPassadoException.class, () -> {
            throw new HorarioNoPassadoException(LocalTime.of(10, 0));
        });
    }
}
