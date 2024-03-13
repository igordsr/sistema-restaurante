package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.exception.SystemException;

public interface IAttributeValidatorRule<T> {
    T validate() throws SystemException;
}
