package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IAlterarStatusReserva;

public interface IAlterarStatusReservaRule {

    Reserva alterarStatusDaReserva(final Reserva reserva);

    static Reserva atualizar(AValidateEntityRole<Reserva> entity, IAlterarStatusReserva repository, Reserva reserva) throws SystemException {
        final Reserva reservaValidada = entity.validate(reserva);
        return repository.aterarStatusDaReserva(reservaValidada);
    }

}
