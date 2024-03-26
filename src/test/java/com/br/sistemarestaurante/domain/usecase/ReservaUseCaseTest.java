package com.br.sistemarestaurante.domain.usecase;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IManterReserva;
import com.br.sistemarestaurante.domain.servicecontracts.IManterRestaurante;
import com.br.sistemarestaurante.infrastructure.persistence.service.ClienteService;
import com.br.sistemarestaurante.infrastructure.persistence.service.ReservaService;
import com.br.sistemarestaurante.infrastructure.persistence.service.RestauranteService;
import com.br.sistemarestaurante.utils.InstanceGeneratorHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class ReservaUseCaseTest {
    private IManterReserva reservaRepository;
    private IManterRestaurante restauranteRepository;
    private IManterCliente clienteRepository;
    private ReservaUseCase reservaUseCase;

    @BeforeEach
    public void setup() {
        reservaRepository = mock(ReservaService.class);
        restauranteRepository = mock(RestauranteService.class);
        clienteRepository = mock(ClienteService.class);
        reservaUseCase = new ReservaUseCase(reservaRepository, restauranteRepository, clienteRepository);
    }

    @Test
    void testRegistrarNoRepositorioDeDados() {
        final Cliente cliente = InstanceGeneratorHelper.gerarCliente();
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        when(restauranteRepository.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));
        when(reservaRepository.listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO)).thenReturn(List.of());
        when(clienteRepository.findClienteByEmail(cliente.getEmail())).thenReturn(Optional.empty());
        when(clienteRepository.resgistar(cliente)).thenReturn(cliente);
        when(reservaRepository.resgistar(reserva)).thenReturn(reserva);

        Reserva result = reservaUseCase.registarNoRepositorioDeDados(reserva);


        Assertions.assertAll(
                () -> Assertions.assertNotNull(result),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaRepository, times(1)).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, times(1)).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, times(1)).resgistar(any(Cliente.class))
        );
    }
}
