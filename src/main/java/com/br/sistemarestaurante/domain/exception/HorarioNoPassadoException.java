package com.br.sistemarestaurante.domain.exception;

public class HorarioNoPassadoException extends SystemException{
    public HorarioNoPassadoException(String hora) {
        super(String.format("Não é possível agendar um horário no passado [%s]. Por favor, escolha uma data e hora futuras para sua reserva.", hora));
    }
}
