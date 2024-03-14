package com.br.sistemarestaurante.domain.usecase.scenario;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.RestauranteNotFoundException;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;
import com.br.sistemarestaurante.domain.servicecontracts.IReservaAlterarStatus;
import com.br.sistemarestaurante.domain.usecase.contract.IReservaContract;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;

import java.util.Objects;
import java.util.Optional;

public class ReservaUseCase implements IReservaContract {


    @Override
    public Reserva registarNoRepositorioDeDados(IRegistrarReserva iRegistrarReserva, IBuscarRestaurante restaurantRepository, IManterCliente clienteRepositorio, Reserva reserva) {
        final Reserva reservaValid = this.validate(iRegistrarReserva, restaurantRepository, clienteRepositorio, reserva);
        return iRegistrarReserva.resgistar(reservaValid);
    }


    @Override
    public Reserva validate(IRegistrarReserva iRegistrarReserva, IBuscarRestaurante restaurantRepository, IManterCliente clienteRepositorio, IAttributeValidatorRule<Reserva> reserva) throws SystemException {
        if (Objects.isNull(reserva)) {
            throw new SystemException("[reserva] não pode ser nulo ou branco");
        }
        Reserva reservaValid = reserva.validate();
        final Restaurante restaurante = restaurantRepository.findRestauranteById(reservaValid.getRestauranteId()).orElseThrow(RestauranteNotFoundException::new);
        final Optional<Cliente> clienteFound = clienteRepositorio.findClienteByEmail(reservaValid.getCliente().getEmail());
        final Cliente cliente = clienteFound.orElseGet(() -> clienteRepositorio.resgistar(reservaValid.getCliente()));
        reservaValid.setCliente(cliente);
        reservaValid.setIdentificador(restaurante.getIdentificador());
        return reservaValid;
    }

    @Override
    public Reserva reservaAlterarStatus(IReservaAlterarStatus iReservaAlterarStatus, Reserva reserva) {
        return iReservaAlterarStatus.reservaAterarStatus(reserva);
    }
}
