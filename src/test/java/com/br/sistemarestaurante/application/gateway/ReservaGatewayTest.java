package com.br.sistemarestaurante.application.gateway;

import com.br.sistemarestaurante.application.dto.ReservaDTO;
import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.exception.*;
import com.br.sistemarestaurante.infrastructure.persistence.service.ClienteService;
import com.br.sistemarestaurante.infrastructure.persistence.service.ReservaService;
import com.br.sistemarestaurante.infrastructure.persistence.service.RestauranteService;
import com.br.sistemarestaurante.utils.InstanceGeneratorHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservaGatewayTest {
    @InjectMocks
    private ReservaGateway reservaGateway;

    @Mock
    private ReservaService reservaService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private RestauranteService restauranteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegistrarNoRepositorioDeDados() {
        final Cliente cliente = InstanceGeneratorHelper.gerarCliente();
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        ReservaDTO reservaDTO = InstanceGeneratorHelper.ReservaToDTO(reserva);
        when(restauranteService.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));
        when(reservaService.listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO)).thenReturn(List.of());
        when(clienteService.findClienteByEmail(cliente.getEmail())).thenReturn(Optional.empty());
        when(clienteService.resgistar(cliente)).thenReturn(cliente);
        when(reservaService.resgistar(any(Reserva.class))).thenReturn(reserva);

        ReservaDTO result = reservaGateway.registrar(reservaDTO);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(result),
                () -> verify(restauranteService, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaService, times(1)).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteService, times(1)).findClienteByEmail(anyString()),
                () -> verify(clienteService, times(1)).resgistar(any(Cliente.class)),
                () -> verify(reservaService, times(1)).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoRestauranteNaoExiste() {
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        ReservaDTO reservaDTO = InstanceGeneratorHelper.ReservaToDTO(reserva);
        when(restauranteService.findRestauranteById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaGateway.registrar(reservaDTO)).isInstanceOf(RestauranteNotFoundException.class).hasMessage("Restaurante não cadastrado na base de dados."),
                () -> verify(restauranteService, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaService, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteService, never()).findClienteByEmail(anyString()),
                () -> verify(clienteService, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaService, never()).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoDataDaReservaForAnteriorADataAtual() {
        final Calendar pastDate = Calendar.getInstance();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        pastDate.add(Calendar.DAY_OF_MONTH, -5);
        reserva.setData(pastDate);
        ReservaDTO reservaDTO = InstanceGeneratorHelper.ReservaToDTO(reserva);
        when(restauranteService.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaGateway.registrar(reservaDTO)).isInstanceOf(DataPassadaException.class).hasMessage("Data inválida. A data não pode ser anterior à data atual."),
                () -> verify(restauranteService, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaService, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteService, never()).findClienteByEmail(anyString()),
                () -> verify(clienteService, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaService, never()).resgistar(any(Reserva.class))
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
        ReservaDTO reservaDTO = InstanceGeneratorHelper.ReservaToDTO(reserva);
        when(restauranteService.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaGateway.registrar(reservaDTO)).isInstanceOf(HorarioNoPassadoException.class).hasMessage(msgError),
                () -> verify(restauranteService, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaService, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteService, never()).findClienteByEmail(anyString()),
                () -> verify(clienteService, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaService, never()).resgistar(any(Reserva.class))
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
        ReservaDTO reservaDTO = InstanceGeneratorHelper.ReservaToDTO(reserva);
        when(restauranteService.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaGateway.registrar(reservaDTO)).isInstanceOf(RestauranteFechadoException.class).hasMessage(msgError),
                () -> verify(restauranteService, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaService, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteService, never()).findClienteByEmail(anyString()),
                () -> verify(clienteService, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaService, never()).resgistar(any(Reserva.class))
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
        ReservaDTO reservaDTO = InstanceGeneratorHelper.ReservaToDTO(reserva);
        when(restauranteService.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaGateway.registrar(reservaDTO)).isInstanceOf(RestauranteFechadoException.class).hasMessage(msgError),
                () -> verify(restauranteService, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaService, never()).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteService, never()).findClienteByEmail(anyString()),
                () -> verify(clienteService, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaService, never()).resgistar(any(Reserva.class))
        );
    }

    @Test
    void testGerarExcecao_QuandoHaDisponibilidadeParaNovasReservas() {
        final Restaurante restaurante = InstanceGeneratorHelper.gerarRestaurante();
        final Reserva reserva = InstanceGeneratorHelper.gerarReserva();
        final List<Reserva> reservas = Collections.unmodifiableList(List.of(reserva, reserva, reserva, reserva, reserva));
        ReservaDTO reservaDTO = InstanceGeneratorHelper.ReservaToDTO(reserva);
        when(restauranteService.findRestauranteById(any(UUID.class))).thenReturn(Optional.of(restaurante));
        when(reservaService.listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO)).thenReturn(reservas);

        Assertions.assertAll(
                () -> assertThatThrownBy(() -> reservaGateway.registrar(reservaDTO)).isInstanceOf(ReservaNaoDisponivelException.class).hasMessage("O restaurante não tem disponibilidade para a data e hora selecionadas"),
                () -> verify(restauranteService, times(1)).findRestauranteById(any(UUID.class)),
                () -> verify(reservaService, times(1)).listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO),
                () -> verify(clienteService, never()).findClienteByEmail(anyString()),
                () -> verify(clienteService, never()).resgistar(any(Cliente.class)),
                () -> verify(reservaService, never()).resgistar(any(Reserva.class))
        );
    }
}
