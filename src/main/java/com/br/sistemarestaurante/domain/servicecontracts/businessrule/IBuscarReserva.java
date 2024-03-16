package com.br.sistemarestaurante.domain.servicecontracts.businessrule;

import com.br.sistemarestaurante.domain.entity.Reserva;

import java.util.List;
import java.util.UUID;

public interface IBuscarReserva {
    List<Reserva> buscarReservaPorRestaurante(UUID restauranteId);
}
