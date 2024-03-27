package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.domain.entity.Cliente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

class ClienteTableTest {

    @Test
    void testToDomainEntity() {
        ClienteTable clienteTable = new ClienteTable();
        UUID clienteId = UUID.randomUUID();
        clienteTable.setId(clienteId);
        clienteTable.setNome("Nome do Cliente");
        clienteTable.setEmail("cliente@example.com");
        clienteTable.setTelefone("123456789");

        Cliente cliente = clienteTable.ToDomainEntity();

        assertEquals(clienteId, cliente.getIdentificador());
        assertEquals("Nome do Cliente", cliente.getNome());
        assertEquals("cliente@example.com", cliente.getEmail());
        assertEquals("123456789", cliente.getTelefone());
    }

    @Test
    void testGetInstance() {
        Cliente cliente = new Cliente(UUID.randomUUID(), "Nome do Cliente", "cliente@example.com", "123456789");

        ClienteTable clienteTable = ClienteTable.getInstance(cliente);

        assertEquals("Nome do Cliente", clienteTable.getNome());
        assertEquals("cliente@example.com", clienteTable.getEmail());
        assertEquals("123456789", clienteTable.getTelefone());
    }

    @Test
    void testOneToManyRelationship() {
        ReservaTable reserva1 = mock(ReservaTable.class);
        ReservaTable reserva2 = mock(ReservaTable.class);

        List<ReservaTable> reservas = new ArrayList<>();
        reservas.add(reserva1);
        reservas.add(reserva2);

        ClienteTable clienteTable = new ClienteTable();
        clienteTable.setReservaTables(reservas);

        assertEquals(reservas.size(), clienteTable.getReservaTables().size());
        assertEquals(reserva1, clienteTable.getReservaTables().get(0));
        assertEquals(reserva2, clienteTable.getReservaTables().get(1));
    }
}
