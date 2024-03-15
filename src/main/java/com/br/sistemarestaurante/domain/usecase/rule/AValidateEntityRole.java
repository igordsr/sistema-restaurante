package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.exception.SystemException;

public interface AValidateEntityRole<T> {
    T validate(IAttributeValidatorRule<T> entity) throws SystemException;
}
