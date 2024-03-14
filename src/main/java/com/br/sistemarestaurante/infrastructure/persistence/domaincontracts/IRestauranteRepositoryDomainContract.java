package com.br.sistemarestaurante.infrastructure.persistence.domaincontracts;

import com.br.sistemarestaurante.domain.exception.RestauranteNotFoundException;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarRestaurante;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRestauranteRepositoryDomainContract extends IRegistrarRestaurante, IBuscarRestaurante {
    RestauranteTable findRestauranteTableById(final UUID id) throws RestauranteNotFoundException;
}
