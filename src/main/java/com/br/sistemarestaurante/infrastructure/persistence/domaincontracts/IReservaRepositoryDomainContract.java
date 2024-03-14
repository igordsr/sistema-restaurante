package com.br.sistemarestaurante.infrastructure.persistence.domaincontracts;

import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;
import com.br.sistemarestaurante.domain.servicecontracts.IReservaAlterarStatus;

public interface IReservaRepositoryDomainContract extends IRegistrarReserva, IReservaAlterarStatus {
}
