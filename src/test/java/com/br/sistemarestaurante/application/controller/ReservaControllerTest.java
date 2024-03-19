package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.ReservaDTO;
import com.br.sistemarestaurante.application.gateway.ReservaGateway;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservaControllerTest {
    @InjectMocks
    private ReservaController reservaController;

    @Mock
    private ReservaGateway reservaGateway;

    ReservaDTO reservaDTOMock = mock(ReservaDTO.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrar() {
        when(reservaGateway.registrar(reservaDTOMock)).thenReturn(reservaDTOMock);

        ReservaDTO result = reservaController.registrar(reservaDTOMock).getBody();

        assertEquals(reservaDTOMock, result);
        verify(reservaGateway, times(1)).registrar(reservaDTOMock);
    }

    @Test
    void deveAlterarStatus() {
        UUID id = UUID.randomUUID();

        StatusReserva status = StatusReserva.RESERVADO;

        when(reservaGateway.alterarStatus(id, status)).thenReturn(reservaDTOMock);

        ReservaDTO result = reservaController.alterarStatus(id, status);

        assertEquals(reservaDTOMock, result);
        verify(reservaGateway, times(1)).alterarStatus(id, status);
    }

    @Test
    void deveBuscarPorRestaurante() {
        UUID restauranteId = UUID.randomUUID();
        List<ReservaDTO> reservas = Collections.singletonList(reservaDTOMock);

        when(reservaGateway.buscarReservaPorRestaurante(restauranteId)).thenReturn(reservas);

        List<ReservaDTO> result = reservaController.buscar(restauranteId);

        assertEquals(reservas, result);
        verify(reservaGateway, times(1)).buscarReservaPorRestaurante(restauranteId);
    }
}
