package com.br.sistemarestaurante.domain.usecase.scenario;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarRestaurante;
import com.br.sistemarestaurante.domain.usecase.contract.IRestauranteUseCase;

public class RestauranteUseCaseImpl implements IRestauranteUseCase {

    @Override
    public Restaurante registarNoRepositorioDeDados(IRegistrarRestaurante iRegistrarRestaurante, Restaurante restaurante) {
        return iRegistrarRestaurante.resgistar(restaurante);
    }
}
