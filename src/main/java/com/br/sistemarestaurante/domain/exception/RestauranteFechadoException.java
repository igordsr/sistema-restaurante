package com.br.sistemarestaurante.domain.exception;

import java.time.LocalTime;

public class RestauranteFechadoException extends SystemException {
    public RestauranteFechadoException(LocalTime horaDaReserva, LocalTime horarioAberturaRestaurante, LocalTime horarioFechamentoRestaurante) {
        super(String.format("Restaurante não atende às %s. Horario de Funcionamento das %s às %s", horaDaReserva, horarioAberturaRestaurante, horarioFechamentoRestaurante));
    }
}
