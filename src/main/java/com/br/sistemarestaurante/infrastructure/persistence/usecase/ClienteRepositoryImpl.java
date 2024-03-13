package com.br.sistemarestaurante.infrastructure.persistence.usecase;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositoryImpl {
    private IClienteRepository iClienteRepository;

    @Autowired
    public ClienteRepositoryImpl(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }

    public ClienteTable findCliente(Cliente cliente) {
        ClienteTable clienteTable = null;
        Optional<ClienteTable> foundCliente = this.iClienteRepository.findByEmail(cliente.getEmail());

        if (foundCliente.isEmpty()) {
            foundCliente = Optional.of(this.saveCliente(cliente));
        }

        clienteTable = foundCliente.get();
        return clienteTable;
    }

    private ClienteTable saveCliente(Cliente cliente) {
        ClienteTable clienteTable = this.convertClienteDomainToClienteDataBaseEntity(cliente);

        return this.iClienteRepository.save(clienteTable);
    }

    private ClienteTable convertClienteDomainToClienteDataBaseEntity(Cliente clienteDomain) {
        final ClienteTable clienteTable = new ClienteTable();
        clienteTable.setEmail(clienteDomain.getEmail());
        clienteTable.setTelefone(clienteDomain.getTelefone());
        clienteTable.setNome(clienteDomain.getNome());
        return clienteTable;
    }
}
