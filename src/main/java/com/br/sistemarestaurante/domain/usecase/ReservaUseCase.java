package com.br.sistemarestaurante.domain.usecase;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.exception.DataPassadaException;
import com.br.sistemarestaurante.domain.exception.ReservaNaoDisponivelException;
import com.br.sistemarestaurante.domain.exception.RestauranteNotFoundException;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IManterReserva;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarReserva;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.usecase.contract.IContractReserva;
import com.br.sistemarestaurante.domain.usecase.rule.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservaUseCase implements IContractReserva {
    private final IManterReserva reservaRepository;
    private final IBuscarRestaurante restaurantRepository;
    private final IManterCliente clienteRepositorio;

    public ReservaUseCase(IManterReserva reservaRepository, IBuscarRestaurante restaurantRepository, IManterCliente clienteRepositorio) {
        this.reservaRepository = reservaRepository;
        this.restaurantRepository = restaurantRepository;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public void verificarDisponibilidadeDaReserva(final Restaurante restaurante, final List<Reserva> reservas) throws ReservaNaoDisponivelException {
        IVerificarDisponibilidadeDaReservaRule.validarDisponibilidadeDaReserva(restaurante, reservas);
    }

    @Override
    public void verificarHoraDaReservaEDoRestaurante(Reserva reserva, Restaurante restaurante) throws SystemException {
        IVerificarHoraDaReservaDoRestauranteRule.validarHorario(reserva, restaurante);
    }

    @Override
    public void verificarDataDaReserva(Reserva reserva) throws DataPassadaException {
        IVerificarDataDaReservaRule.isValid(reserva.getData());
    }

    @Override
    public Reserva validate(IAttributeValidatorRule<Reserva> preReserva) throws SystemException {
        Reserva reserva = IAttributeValidatorRule.validar(preReserva);
        final Restaurante restaurante = restaurantRepository.findRestauranteById(reserva.getRestauranteId()).orElseThrow(RestauranteNotFoundException::new);
        this.verificarDataDaReserva(reserva);
        this.verificarHoraDaReservaEDoRestaurante(reserva, restaurante);
        final List<Reserva> reservas = reservaRepository.listarReservasRestaurantePeloStatusReserva(restaurante, reserva.getData(), reserva.getHora(), StatusReserva.RESERVADO);
        this.verificarDisponibilidadeDaReserva(restaurante, reservas);
        final Optional<Cliente> clienteFound = clienteRepositorio.findClienteByEmail(reserva.getCliente().getEmail());
        final Cliente cliente = clienteFound.orElseGet(() -> clienteRepositorio.resgistar(reserva.getCliente()));
        reserva.setCliente(cliente);
        reserva.setIdentificador(restaurante.getIdentificador());
        return reserva;
    }

    @Override
    public Reserva registarNoRepositorioDeDados(Reserva reserva) throws SystemException {
        return IRegistrarReservaRule.salvar(this, this.reservaRepository, reserva);
    }

    @Override
    public Reserva alterarStatusDaReserva(Reserva reserva) throws SystemException {
        return IAlterarStatusReservaRule.atualizar(this.reservaRepository, reserva);
    }

    @Override
    public List<Reserva> buscarReservaPorRestaurante(IBuscarReserva repository, UUID restauranteId) throws SystemException {
        return repository.buscarReservaPorRestaurante(restauranteId);
    }
}
