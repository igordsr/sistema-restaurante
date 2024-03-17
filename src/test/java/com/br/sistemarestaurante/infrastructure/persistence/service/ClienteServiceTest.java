package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.exception.ClienteNotFoundException;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepositoryMock;

    @InjectMocks
    private ClienteService clienteService;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveBuscarPorEmail() {
        ClienteTable clienteTableMock = mock(ClienteTable.class);
        when(clienteRepositoryMock.findByEmail(any())).thenReturn(Optional.of(clienteTableMock));

        Cliente clienteMock = mock(Cliente.class);
        when(clienteTableMock.ToDomainEntity()).thenReturn(clienteMock);

        Optional<Cliente> result = clienteService.findClienteByEmail(anyString());

        assertEquals(clienteMock, result.orElse(null));

        verify(clienteRepositoryMock, times(1)).findByEmail(any());
    }

    @Test
    void deveRegistrar() {
        Cliente clienteMock = mock(Cliente.class);
        ClienteTable clienteTableMock = mock(ClienteTable.class);

        when(clienteTableMock.ToDomainEntity()).thenReturn(clienteMock);
        when(clienteRepositoryMock.save(any())).thenReturn(clienteTableMock);

        Cliente result = clienteService.resgistar(clienteMock);

        assertEquals(clienteMock, result);

        verify(clienteRepositoryMock, times(1)).save(any());
    }

    @Test
    void deveBuscarPorId() {
        UUID id = UUID.randomUUID();
        ClienteTable clienteTableMock = mock(ClienteTable.class);
        when(clienteRepositoryMock.findById(id)).thenReturn(Optional.of(clienteTableMock));

        ClienteTable result = clienteService.findClienteById(id);

        assertEquals(clienteTableMock, result);

        verify(clienteRepositoryMock, times(1)).findById(id);
    }

    @Test
    void deveBuscarPorId_ClienteNotFoundException() {
        UUID id = UUID.randomUUID();
        when(clienteRepositoryMock.findById(id)).thenReturn(Optional.empty());

        assertThrows(ClienteNotFoundException.class, () -> clienteService.findClienteById(id));

        verify(clienteRepositoryMock, times(1)).findById(id);
    }
}
