package com.br.sistemarestaurante.infrastructure.persistence.domaincontracts;

import com.br.sistemarestaurante.domain.exception.AvaliacaoNotFoundException;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarAvaliacao;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IRegistrarAvaliacao;
import com.br.sistemarestaurante.infrastructure.persistence.entity.AvaliacaoTable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAvaliacaoRepositoryDomainContract extends IRegistrarAvaliacao, IBuscarAvaliacao {
    AvaliacaoTable findAvaliacaoTableById(final UUID id) throws AvaliacaoNotFoundException;
}
