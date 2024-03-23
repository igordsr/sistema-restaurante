package com.br.sistemarestaurante.domain.usecase;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IManterAvaliacao;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarAvaliacao;
import com.br.sistemarestaurante.domain.usecase.contract.IAvaliacaoContract;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarAvaliacaoRule;

import java.util.List;
import java.util.UUID;

public class AvaliacaoUseCase implements IAvaliacaoContract {
    private final IManterAvaliacao repository;

    public AvaliacaoUseCase(IManterAvaliacao repository) {
        this.repository = repository;
    }

    @Override
    public Avaliacao registarNoRepositorioDeDados(Avaliacao avaliacao) {
        return IRegistrarAvaliacaoRule.salvar(this, repository, avaliacao);
    }

    @Override
    public Avaliacao validate(IAttributeValidatorRule<Avaliacao> avaliacao) throws SystemException {
        return IAttributeValidatorRule.validar(avaliacao);
    }



    @Override
    public List<Avaliacao> buscarAvaliacaoPorReserva(IBuscarAvaliacao repository, UUID reservaId) {
        return repository.findByReservaTable(reservaId);
    }
}
