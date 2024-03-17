package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.AvaliacaoNotFoundException;
import com.br.sistemarestaurante.domain.exception.RestauranteNotFoundException;
import com.br.sistemarestaurante.infrastructure.persistence.domaincontracts.IAvaliacaoRepositoryDomainContract;
import com.br.sistemarestaurante.infrastructure.persistence.entity.AvaliacaoTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AvaliacaoService implements IAvaliacaoRepositoryDomainContract {
    private IAvaliacaoRepository repository;

    @Autowired
    public AvaliacaoService(IAvaliacaoRepository iAvaliacaoRepository) {
        this.repository = iAvaliacaoRepository;
    }


    @Override
    public Optional<Avaliacao> findAvaliacaoById(UUID id) {
        return this.repository.findById(id).map(AvaliacaoTable::ToDomainEntity);
    }

    @Override
    public List<Avaliacao> findByReservaId(UUID id) {
        return this.findByReservaId(id);
    }

    @Override
    public AvaliacaoTable findAvaliacaoTableById(UUID id) throws AvaliacaoNotFoundException {
        return null;
    }

    @Override
    public Avaliacao registrar(Avaliacao avaliacao) {
        return null;
    }
}