package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IRegistrarRestaurante;

public interface IRegistrarRestauranteRule {
    Restaurante registarNoRepositorioDeDados(final Restaurante restaurante);

    static Restaurante salvar(AValidateEntityRole<Restaurante> entity, IRegistrarRestaurante repository, Restaurante restaurante) throws SystemException {
        final Restaurante validated = entity.validate(restaurante);
        return repository.resgistar(validated);
    }
}
