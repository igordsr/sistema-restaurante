package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.exception.RestauranteException;

public interface IAttributeValidator<T> {
    T validate() throws RestauranteException;
}
