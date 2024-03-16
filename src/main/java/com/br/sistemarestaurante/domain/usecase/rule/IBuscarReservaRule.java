package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarReserva;

import java.util.List;
import java.util.UUID;

public interface IBuscarReservaRule {

    List<Reserva> buscarReservaPorRestaurante(IBuscarReserva repository, UUID restauranteId);


}
