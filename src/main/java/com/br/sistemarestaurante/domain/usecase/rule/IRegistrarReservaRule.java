package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IManterReserva;

public interface IRegistrarReservaRule {
    Reserva registarNoRepositorioDeDados(final IManterReserva reservaRepository, final IBuscarRestaurante restauranteRepositorio, final IManterCliente clienteRepositorio, Reserva reserva);
}
