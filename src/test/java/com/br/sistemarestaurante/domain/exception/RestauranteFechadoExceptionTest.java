package com.br.sistemarestaurante.domain.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteFechadoExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        LocalTime horaDaReserva = LocalTime.of(12, 30);
        LocalTime horarioAberturaRestaurante = LocalTime.of(10, 0);
        LocalTime horarioFechamentoRestaurante = LocalTime.of(22, 0);

        RestauranteFechadoException exception = new RestauranteFechadoException(horaDaReserva, horarioAberturaRestaurante, horarioFechamentoRestaurante);

        String expectedMessage = String.format("Restaurante não atende às %s. Horario de Funcionamento das %s às %s", horaDaReserva, horarioAberturaRestaurante, horarioFechamentoRestaurante);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testThrowRestauranteFechadoException() {
        LocalTime horaDaReserva = LocalTime.of(12, 30);
        LocalTime horarioAberturaRestaurante = LocalTime.of(10, 0);
        LocalTime horarioFechamentoRestaurante = LocalTime.of(22, 0);

        assertThrows(RestauranteFechadoException.class, () -> {
            throw new RestauranteFechadoException(horaDaReserva, horarioAberturaRestaurante, horarioFechamentoRestaurante);
        });
    }
}
