package com.br.sistemarestaurante.domain.exception;

public final class ClienteNotFoundException extends SystemException {
    public ClienteNotFoundException() {
        super("Cliente não cadastrado na base de dados.");
    }
}
