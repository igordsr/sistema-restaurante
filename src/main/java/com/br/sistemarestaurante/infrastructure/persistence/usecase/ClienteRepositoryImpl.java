package com.br.sistemarestaurante.infrastructure.persistence.usecase;

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
public class ClienteRepositoryImpl implements IClienteRepositoryDomainContract {
    private IClienteRepository iClienteRepository;

    @Autowired
    public ClienteRepositoryImpl(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    @Override
    public Optional<Cliente> findClienteByEmail(String email) {
        final Optional<ClienteTable> foundCliente = this.iClienteRepository.findByEmail(email);
        return foundCliente.map(ClienteTable::ToDomainEntity);
    }

    @Override
    public Cliente resgistar(Cliente cliente) {
        ClienteTable clienteTable = this.convertClienteDomainToClienteDataBaseEntity(cliente);
        return this.iClienteRepository.save(clienteTable).ToDomainEntity();
    }

    @Override
    public ClienteTable findClienteById(UUID id) throws ClienteNotFoundException {
        return this.iClienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

    private ClienteTable convertClienteDomainToClienteDataBaseEntity(Cliente clienteDomain) {
        final ClienteTable clienteTable = new ClienteTable();
        clienteTable.setEmail(clienteDomain.getEmail());
        clienteTable.setTelefone(clienteDomain.getTelefone());
        clienteTable.setNome(clienteDomain.getNome());
        return clienteTable;
    }


}
