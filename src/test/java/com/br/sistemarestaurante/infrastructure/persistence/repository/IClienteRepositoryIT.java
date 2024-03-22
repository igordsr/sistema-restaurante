package com.br.sistemarestaurante.infrastructure.persistence.repository;

import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class IClienteRepositoryIT {

    @Autowired
    private IClienteRepository clienteRepository;

    @Test
    void devePermitirCriarTabela() {
        var totalRegistros = clienteRepository.count();
        assertThat(totalRegistros).isGreaterThan(0);
    }

    @Test
    void devePermitirRegistrarCliente() {
        ClienteTable clienteTable = new ClienteTable();
        clienteTable.setNome("Nome Teste");
        clienteTable.setEmail("teste@teste.com");
        clienteTable.setTelefone("11999999999");

        ClienteTable clienteRegistrado = clienteRepository.save(clienteTable);

        assertThat(clienteRegistrado.getEmail()).isEqualTo("teste@teste.com");
    }

    @Test
    void devePermitirEncontrarClientePorEmail() {
        ClienteTable cliente = registrarCliente("teste@example.com");

        Optional<ClienteTable> clienteOptional = clienteRepository.findByEmail("teste@example.com");

        assertThat(clienteOptional).isPresent();
        assertThat(clienteOptional.get().getId()).isEqualTo(cliente.getId());
        assertThat(clienteOptional.get().getEmail()).isEqualTo(cliente.getEmail());
    }

    @Test
    void naoDeveEncontrarClienteComEmailInexistente() {
        Optional<ClienteTable> clienteOptional = clienteRepository.findByEmail("email.inexistente@example.com");

        assertThat(clienteOptional).isEmpty();
    }

    private ClienteTable registrarCliente(String email) {
        ClienteTable cliente = new ClienteTable();
        cliente.setEmail(email);
        cliente.setNome("Teste");
        cliente.setTelefone("123456789");
        return clienteRepository.save(cliente);
    }
}
