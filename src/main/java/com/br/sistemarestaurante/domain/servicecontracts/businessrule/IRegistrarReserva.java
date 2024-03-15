package com.br.sistemarestaurante.domain.servicecontracts.businessrule;

import com.br.sistemarestaurante.domain.entity.Reserva;

public interface IRegistrarReserva {
    Reserva resgistar(final Reserva reserva);
}
