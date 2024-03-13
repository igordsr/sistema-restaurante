package com.br.sistemarestaurante.domain.servicecontracts;

import com.br.sistemarestaurante.domain.entity.Reserva;

public interface IRegistrarReserva {
    Reserva resgistar(final Reserva reserva);
}
