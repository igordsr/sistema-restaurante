package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestauranteById;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;

public interface IRegistrarReservaRule {
    Reserva registarNoRepositorioDeDados(final IRegistrarReserva iRegistrarReserva, final IBuscarRestauranteById restauranteRepositorio, final IManterCliente clienteRepositorio, Reserva reserva);
}
