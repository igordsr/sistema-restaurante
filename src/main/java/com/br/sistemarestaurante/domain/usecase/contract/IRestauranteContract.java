package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import com.br.sistemarestaurante.domain.usecase.rule.IBuscarRestauranteRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarRestauranteRule;

public interface IRestauranteContract extends IRegistrarRestauranteRule, IBuscarRestauranteRule {

    Restaurante validate(IAttributeValidatorRule<Restaurante> restaurante) throws SystemException;
}
