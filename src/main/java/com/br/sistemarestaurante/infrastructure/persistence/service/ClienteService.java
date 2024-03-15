package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.exception.ClienteNotFoundException;
import com.br.sistemarestaurante.infrastructure.persistence.domaincontracts.IClienteRepositoryDomainContract;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClienteService implements IClienteRepositoryDomainContract {
    private final IClienteRepository iClienteRepository;

    @Autowired
    public ClienteService(final IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    @Override
    public Optional<Cliente> findClienteByEmail(String email) {
        final Optional<ClienteTable> foundCliente = this.iClienteRepository.findByEmail(email);
        return foundCliente.map(ClienteTable::ToDomainEntity);
    }

    @Override
    public Cliente resgistar(Cliente cliente) {
        ClienteTable clienteTable = ClienteTable.getInstance(cliente);
        return this.iClienteRepository.save(clienteTable).ToDomainEntity();
    }

    @Override
    public ClienteTable findClienteById(UUID id) throws ClienteNotFoundException {
        return this.iClienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }
}
