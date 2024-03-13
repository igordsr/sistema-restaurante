package com.br.sistemarestaurante.domain.servicecontracts;

import com.br.sistemarestaurante.domain.entity.Cliente;

import java.util.Optional;

public interface IManterCliente {
    Cliente resgistar(final Cliente cliente);

    Optional<Cliente> findClienteByEmail(final String email);
}
