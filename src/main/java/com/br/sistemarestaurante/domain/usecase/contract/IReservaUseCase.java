package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.ReservaException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidator;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarReservaUseCase;

public interface IReservaUseCase extends IRegistrarReservaUseCase {

    Reserva validate(IAttributeValidator<Reserva> reserva) throws ReservaException;
}
