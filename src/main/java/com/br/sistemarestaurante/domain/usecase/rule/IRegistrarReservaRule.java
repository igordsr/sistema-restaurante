package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.DataNaoPassadaException;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;

public interface IRegistrarReservaRule {

    Reserva registarNoRepositorioDeDados(Reserva reserva);

    static Reserva salvar(AValidateEntityRole<Reserva> entity, IRegistrarReserva repository, Reserva reserva) throws DataNaoPassadaException {
        final Reserva reservaValidada = entity.validate(reserva);
        return repository.resgistar(reservaValidada);
    }
}
