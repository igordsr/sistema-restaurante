package com.br.sistemarestaurante.domain.exception;

public final class AvaliacaoNotFoundException extends SystemException {
    public AvaliacaoNotFoundException() {
        super("Cliente não cadastrado na base de dados.");
    }
}
