package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;

public interface IRegistrarReservaUseCase {
    Reserva registarNoRepositorioDeDados(final IRegistrarReserva iRegistrarReserva, final Reserva reserva);
}
