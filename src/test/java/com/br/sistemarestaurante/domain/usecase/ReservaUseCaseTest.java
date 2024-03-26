package com.br.sistemarestaurante.domain.usecase;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.exception.*;
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
import org.mockito.Mock;

import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ReservaUseCaseTest {
    @Mock
    private IManterReserva reservaRepository;
    @Mock
    private IManterRestaurante restauranteRepository;
    @Mock
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
                () -> verify(reservaRepository, times(1)).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, times(1)).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, times(1)).resgistar(any(Cliente.class)),
                () -> verify(reservaRepository, times(1)).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoRestauranteNaoExiste() {
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        when(restauranteRepository.findRestauranteById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaUseCase.registarNoRepositorioDeDados(reserva)).isInstanceOf(RestauranteNotFoundException.class).hasMessage("Restaurante não cadastrado na base de dados."),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaRepository, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, never()).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaRepository, never()).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoDataDaReservaForAnteriorADataAtual() {
        final Calendar pastDate = Calendar.getInstance();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        pastDate.add(Calendar.DAY_OF_MONTH, -5);
        reserva.setData(pastDate);
        when(restauranteRepository.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaUseCase.registarNoRepositorioDeDados(reserva)).isInstanceOf(DataPassadaException.class).hasMessage("Data inválida. A data não pode ser anterior à data atual."),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaRepository, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, never()).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaRepository, never()).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoDataDaReservaForDataAtualEHoraPassada() {
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        reserva.setHora(LocalTime.now().minusHours(1));
        reserva.setData(Calendar.getInstance());
        restaurante.setHorarioAbertura(LocalTime.now().minusHours(2));
        final String msgError = String.format("Não é possível agendar um horário no passado [%s]. Por favor, escolha uma data e hora futuras para sua reserva.", reserva.getHora().format(HorarioNoPassadoException.formatter));

        when(restauranteRepository.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaUseCase.registarNoRepositorioDeDados(reserva)).isInstanceOf(HorarioNoPassadoException.class).hasMessage(msgError),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaRepository, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, never()).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaRepository, never()).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoRestauranteDiurnoEstaFechado() {
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        reserva.setHora(LocalTime.of(18, 45));
        final String msgError = String.format("Restaurante não atende às %s. Horario de Funcionamento das %s às %s",
                reserva.getHora().format(RestauranteFechadoException.formatter),
                restaurante.getHorarioAbertura().format(RestauranteFechadoException.formatter),
                restaurante.getHorarioFechamento().format(RestauranteFechadoException.formatter)
        );

        when(restauranteRepository.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaUseCase.registarNoRepositorioDeDados(reserva)).isInstanceOf(RestauranteFechadoException.class).hasMessage(msgError),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaRepository, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, never()).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaRepository, never()).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoRestauranteNoturnoEstaFechado() {
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        restaurante.setHorarioAbertura(LocalTime.of(20, 45));
        restaurante.setHorarioFechamento(LocalTime.of(2, 45));
        reserva.setHora(LocalTime.of(18, 45));
        final String msgError = String.format("Restaurante não atende às %s. Horario de Funcionamento das %s às %s",
                reserva.getHora().format(RestauranteFechadoException.formatter),
                restaurante.getHorarioAbertura().format(RestauranteFechadoException.formatter),
                restaurante.getHorarioFechamento().format(RestauranteFechadoException.formatter)
        );

        when(restauranteRepository.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaUseCase.registarNoRepositorioDeDados(reserva)).isInstanceOf(RestauranteFechadoException.class).hasMessage(msgError),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaRepository, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, never()).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaRepository, never()).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoHaDisponibilidadeParaNovasReservas() {
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        final List<Reserva> reservas = Collections.unmodifiableList(List.of(reserva, reserva, reserva, reserva, reserva));
        when(restauranteRepository.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));
        when(reservaRepository.listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO)).thenReturn(reservas);

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaUseCase.registarNoRepositorioDeDados(reserva)).isInstanceOf(ReservaNaoDisponivelException.class).hasMessage("O restaurante não tem disponibilidade para a data e hora selecionadas"),
                () -> verify(restauranteRepository, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaRepository, times(1)).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteRepository, never()).findClienteByEmail(anyString()),
                () -> verify(clienteRepository, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaRepository, never()).resgistar(any(Reserva.class))
        );
    }
}
