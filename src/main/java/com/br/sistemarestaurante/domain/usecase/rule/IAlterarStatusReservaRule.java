package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IAlterarStatusReserva;

public interface IAlterarStatusReservaRule {

    Reserva alterarStatusDaReserva(final Reserva reserva);

    static Reserva atualizar(IAlterarStatusReserva repository, Reserva reserva) throws SystemException {
        return repository.aterarStatusDaReserva(reserva);
    }

}
