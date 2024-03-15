package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.exception.RestauranteFechadoException;
import com.br.sistemarestaurante.domain.exception.SystemException;

import java.util.Objects;

public interface IAttributeValidatorRule<T> {
    T validate() throws SystemException;

    static <T> T validar(IAttributeValidatorRule<T> obj) throws RestauranteFechadoException {
        if (Objects.isNull(obj)) {
            throw new SystemException("Objeto nulo");
        }
        return obj.validate();
    }
}