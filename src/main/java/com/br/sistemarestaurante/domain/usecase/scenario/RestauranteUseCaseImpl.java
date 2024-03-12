package com.br.sistemarestaurante.domain.usecase.scenario;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.RestauranteException;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarRestaurante;
import com.br.sistemarestaurante.domain.usecase.contract.IRestauranteUseCase;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidator;

import java.util.Objects;

public class RestauranteUseCaseImpl implements IRestauranteUseCase {

    @Override
    public Restaurante registarNoRepositorioDeDados(IRegistrarRestaurante iRegistrarRestaurante, Restaurante restaurante) {
        return iRegistrarRestaurante.resgistar(restaurante);
    }

    @Override
    public Restaurante validate(IAttributeValidator<Restaurante> restaurante) throws RestauranteException {
        if (Objects.isNull(restaurante)) {
            throw new RestauranteException("[restaurante] não pode ser nulo ou branco");
        }
        return restaurante.validate();
    }
}
