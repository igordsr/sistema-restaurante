package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.servicecontracts.IReservaAlterarStatus;

public interface IReservaAlterarStatusRule {

    Reserva reservaAlterarStatus(final IReservaAlterarStatus iReservaAlterarStatus, final Reserva reserva);

}
