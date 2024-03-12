package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidator;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarRestauranteUseCase;
import com.br.sistemarestaurante.domain.exception.RestauranteException;

public interface IRestauranteUseCase extends IRegistrarRestauranteUseCase {

    Restaurante validate(IAttributeValidator<Restaurante> restaurante) throws RestauranteException;
}
