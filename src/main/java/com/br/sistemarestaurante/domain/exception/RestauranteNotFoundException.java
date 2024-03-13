package com.br.sistemarestaurante.domain.exception;

public final class RestauranteNotFoundException extends SystemException {
    public RestauranteNotFoundException() {
        super("Restaurante não cadastrado na base de dados.");
    }
}
