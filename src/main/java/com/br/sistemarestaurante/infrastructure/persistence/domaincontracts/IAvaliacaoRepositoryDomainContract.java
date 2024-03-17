package com.br.sistemarestaurante.infrastructure.persistence.domaincontracts;

import com.br.sistemarestaurante.domain.exception.AvaliacaoNotFoundException;
import com.br.sistemarestaurante.domain.servicecontracts.IManterAvaliacao;
import com.br.sistemarestaurante.infrastructure.persistence.entity.AvaliacaoTable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAvaliacaoRepositoryDomainContract extends IManterAvaliacao {
    AvaliacaoTable findAvaliacaoTableById(final UUID id) throws AvaliacaoNotFoundException;
}
