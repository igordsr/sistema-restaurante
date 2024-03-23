package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {
    @Mock
    private IReservaRepository reservaRepository;
    @Mock
    private RestauranteService restauranteService;
    @Mock
    private ClienteService clienteService;

    private ReservaService reservaService;

    RestauranteService restauranteServiceMock = mock(RestauranteService.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(restauranteServiceMock.findRestauranteTableById(any(UUID.class))).thenReturn(new RestauranteTable());
        reservaService = new ReservaService(reservaRepository, restauranteService, clienteService);
    }

    @Test
    void deveRegistrarReserva() {
        UUID restauranteId = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();

        Reserva reserva = mock(Reserva.class);
        reserva.setRestauranteId(UUID.randomUUID());

        RestauranteTable restauranteTable = mock(RestauranteTable.class);
        ClienteTable clienteTable = mock(ClienteTable.class);

        ReservaTable reservaTable = ReservaTable.getInstance(reserva, restauranteTable, clienteTable);

        when(reserva.getCliente()).thenReturn(mock(Cliente.class));
        when(restauranteService.findRestauranteTableById(restauranteId)).thenReturn(restauranteTable);
        when(clienteService.findClienteById(clienteId)).thenReturn(clienteTable);

        when(reservaRepository.save(any(ReservaTable.class))).thenReturn(reservaTable);

        Reserva result = reservaService.resgistar(reserva);

        assertNotNull(result);
        assertEquals(reserva.getIdentificador(), result.getIdentificador());
    }

    @Test
    void testListarReservasRestaurantePeloStatusReserva() {
        Restaurante restaurante = new Restaurante(UUID.randomUUID(), "Restaurante Teste", "Local", "Cozinha", LocalTime.of(10, 0), LocalTime.of(22, 0), 50);
        Calendar data = Calendar.getInstance();
        LocalTime hora = LocalTime.of(12, 0);
        StatusReserva statusReserva = StatusReserva.RESERVADO;

        Reserva reserva = mock(Reserva.class);
        reserva.setRestauranteId(UUID.randomUUID());

        RestauranteTable restauranteTable = mock(RestauranteTable.class);
        ClienteTable clienteTable = mock(ClienteTable.class);

        ReservaTable reservaTable = ReservaTable.getInstance(reserva, restauranteTable, clienteTable);

        List<ReservaTable> reservaTables = Collections.singletonList(reservaTable);
        when(reservaRepository.findByRestauranteAndDataAndHoraAndStatus(any(RestauranteTable.class), any(Calendar.class), any(LocalTime.class), any(StatusReserva.class))).thenReturn(reservaTables);

        List<Reserva> result = reservaService.listarReservasRestaurantePeloStatusReserva(restaurante, data, hora, statusReserva);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testAlterarStatusDaReserva() {
        UUID reservaId = UUID.randomUUID();
        Reserva reserva = new Reserva(reservaId, UUID.randomUUID(), new Cliente(UUID.randomUUID(), "Cliente", "cliente@test.com", "123456789"), Calendar.getInstance(), LocalTime.of(12, 0), StatusReserva.RESERVADO);

        reserva.setRestauranteId(UUID.randomUUID());

        RestauranteTable restauranteTable = mock(RestauranteTable.class);
        ClienteTable clienteTable = mock(ClienteTable.class);

        ReservaTable reservaTable = ReservaTable.getInstance(reserva, restauranteTable, clienteTable);
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaTable));
        when(reservaRepository.save(any(ReservaTable.class))).thenReturn(reservaTable);

        Reserva result = reservaService.aterarStatusDaReserva(reserva);

        assertNotNull(result);
        assertEquals(reservaId, result.getIdentificador());
    }

    @Test
    void testBuscarReservaPorRestaurante() {
        UUID restauranteId = UUID.randomUUID();
        Reserva reserva = mock(Reserva.class);

        RestauranteTable restauranteTable = mock(RestauranteTable.class);
        ClienteTable clienteTable = mock(ClienteTable.class);

        ReservaTable reservaTable = ReservaTable.getInstance(reserva, restauranteTable, clienteTable);
        List<ReservaTable> reservaTables = Collections.singletonList(reservaTable);
        when(reservaRepository.findByRestauranteId(restauranteId)).thenReturn(reservaTables);

        List<Reserva> result = reservaService.buscarReservaPorRestaurante(restauranteId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
