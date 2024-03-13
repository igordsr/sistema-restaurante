package com.br.sistemarestaurante.domain.usecase.scenario;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.ReservaException;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;
import com.br.sistemarestaurante.domain.usecase.contract.IReservaUseCase;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidator;

import java.util.Objects;

public class ReservaUseCaseImpl implements IReservaUseCase {

    @Override
    public Reserva registarNoRepositorioDeDados(IRegistrarReserva iRegistrarReserva, Reserva reserva) {
        return iRegistrarReserva.resgistar(reserva);
    }


    @Override
    public Reserva validate(IAttributeValidator<Reserva> reserva) throws ReservaException {
        if (Objects.isNull(reserva)) {
            throw new ReservaException("[reserva] não pode ser nulo ou branco");
        }
        return reserva.validate();
    }
}
