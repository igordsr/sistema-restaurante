package com.br.sistemarestaurante.domain.usecase;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IManterRestaurante;
import com.br.sistemarestaurante.domain.usecase.contract.IRestauranteContract;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarRestauranteRule;

import java.util.List;

public class RestauranteUseCase implements IRestauranteContract {
    private final IManterRestaurante repository;

    public RestauranteUseCase(IManterRestaurante repository) {
        this.repository = repository;
    }

    @Override
    public Restaurante registarNoRepositorioDeDados(Restaurante restaurante) {
        return IRegistrarRestauranteRule.salvar(this, repository, restaurante);
    }

    @Override
    public Restaurante validate(IAttributeValidatorRule<Restaurante> restaurante) throws SystemException {
        return IAttributeValidatorRule.validar(restaurante);
    }

    @Override
    public List<Restaurante> findByNomeOrLocalizacaoOrTipoCozinha(Restaurante restaurante) {
        return repository.findByNomeOrLocalizacaoOrTipoCozinha(restaurante);
    }
}
