package com.br.sistemarestaurante.domain.servicecontracts;

import com.br.sistemarestaurante.domain.entity.Reserva;

public interface IReservaAlterarStatus {

    Reserva reservaAterarStatus(final Reserva reserva);
}
