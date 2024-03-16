package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IRegistrarAvaliacao;

public interface IRegistrarAvaliacaoRule {
    Avaliacao registarNoRepositorioDeDados(final Avaliacao avaliacao);

    static Avaliacao salvar(AValidateEntityRole<Avaliacao> entity, IRegistrarAvaliacao repository, Avaliacao avaliacao) throws SystemException {
        final Avaliacao validated = entity.validate(avaliacao);
        return repository.registrar(validated);
    }
}
