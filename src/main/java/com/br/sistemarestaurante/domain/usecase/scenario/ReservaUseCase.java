package com.br.sistemarestaurante.domain.usecase.scenario;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.exception.ReservaNaoDisponivelException;
import com.br.sistemarestaurante.domain.exception.RestauranteNotFoundException;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IManterReserva;
import com.br.sistemarestaurante.domain.usecase.contract.IReservaContract;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ReservaUseCase implements IReservaContract {

    @Override
    public boolean verificarDisponibilidadeDaReserva(final Restaurante restaurante, final List<Reserva> reservas) {
        final int mesasDisponiveis = restaurante.getCapacidade() - reservas.size();
        return mesasDisponiveis > 0;
    }

    @Override
    public Reserva registarNoRepositorioDeDados(IManterReserva reservaRepositorya, IBuscarRestaurante restaurantRepository, IManterCliente clienteRepositorio, Reserva reserva) {
        final Reserva reservaValid = this.validate(reservaRepositorya, restaurantRepository, clienteRepositorio, reserva);
        return reservaRepositorya.resgistar(reservaValid);
    }

    @Override
    public Reserva validate(IManterReserva reservaRepository, IBuscarRestaurante restaurantRepository, IManterCliente clienteRepositorio, IAttributeValidatorRule<Reserva> reserva) throws SystemException {
        if (Objects.isNull(reserva)) {
            throw new SystemException("[reserva] não pode ser nulo ou branco");
        }
        Reserva reservaValid = reserva.validate();
        final Restaurante restaurante = restaurantRepository.findRestauranteById(reservaValid.getRestauranteId()).orElseThrow(RestauranteNotFoundException::new);
        final List<Reserva> reservas = reservaRepository.listarReservasRestaurantePeloStatusReserva(restaurante, reservaValid.getData(), reservaValid.getHora(), StatusReserva.RESERVADO);
        if (!this.verificarDisponibilidadeDaReserva(restaurante, reservas)) {
            throw new ReservaNaoDisponivelException();
        }
        final Optional<Cliente> clienteFound = clienteRepositorio.findClienteByEmail(reservaValid.getCliente().getEmail());
        final Cliente cliente = clienteFound.orElseGet(() -> clienteRepositorio.resgistar(reservaValid.getCliente()));
        reservaValid.setCliente(cliente);
        reservaValid.setIdentificador(restaurante.getIdentificador());
        return reservaValid;
    }


}
