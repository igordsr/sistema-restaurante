package com.br.sistemarestaurante.domain.exception;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RestauranteFechadoException extends SystemException {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public RestauranteFechadoException(LocalTime horaDaReserva, LocalTime horarioAberturaRestaurante, LocalTime horarioFechamentoRestaurante) {
        super(String.format("Restaurante não atende às %s. Horario de Funcionamento das %s às %s", horaDaReserva.format(formatter), horarioAberturaRestaurante.format(formatter), horarioFechamentoRestaurante.format(formatter)));
    }
}
