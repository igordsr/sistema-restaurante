package com.br.sistemarestaurante.domain.exception;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HorarioNoPassadoException extends SystemException {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public HorarioNoPassadoException(LocalTime hora) {
        super(String.format("Não é possível agendar um horário no passado [%s]. Por favor, escolha uma data e hora futuras para sua reserva.", hora.format(formatter)));
    }
}
