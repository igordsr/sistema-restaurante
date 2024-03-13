package com.br.sistemarestaurante.domain.usecase.scenario;

import com.br.sistemarestaurante.adapter.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarRestaurante;
import com.br.sistemarestaurante.domain.usecase.contract.IRestauranteContract;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;

import java.util.List;
import java.util.Objects;

public class RestauranteUseCase implements IRestauranteContract {

    @Override
    public Restaurante registarNoRepositorioDeDados(IRegistrarRestaurante iRegistrarRestaurante, Restaurante restaurante) {
        return iRegistrarRestaurante.resgistar(restaurante);
    }

    @Override
    public Restaurante validate(IAttributeValidatorRule<Restaurante> restaurante) throws SystemException {
        if (Objects.isNull(restaurante)) {
            throw new SystemException("[restaurante] não pode ser nulo ou branco");
        }
        return restaurante.validate();
    }

    @Override
    public List<RestauranteTable> findByNomeOrLocalizacaoOrTipoCozinha(IBuscarRestaurante iBuscarRestaurante, RestauranteSearchDTO restauranteSearchDTO) {
        return iBuscarRestaurante.findByNomeOrLocalizacaoOrTipoCozinha(restauranteSearchDTO);
    }
}
