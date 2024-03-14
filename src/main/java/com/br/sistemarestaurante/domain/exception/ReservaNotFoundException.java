package com.br.sistemarestaurante.domain.exception;

public final class ReservaNotFoundException extends SystemException {
    public ReservaNotFoundException() {
        super("Reserva não cadastrado na base de dados.");
    }
}
