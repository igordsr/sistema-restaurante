package com.br.sistemarestaurante.infrastructure.persistence.domaincontracts;

import com.br.sistemarestaurante.domain.exception.ClienteNotFoundException;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;

import java.util.UUID;

public interface IClienteRepositoryDomainContract extends IManterCliente {
    ClienteTable findClienteById(UUID id) throws ClienteNotFoundException;
}
