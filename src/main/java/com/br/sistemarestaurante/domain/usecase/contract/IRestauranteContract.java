package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarRestauranteRule;
import com.br.sistemarestaurante.domain.exception.SystemException;

public interface IRestauranteContract extends IRegistrarRestauranteRule {

    Restaurante validate(IAttributeValidatorRule<Restaurante> restaurante) throws SystemException;
}
