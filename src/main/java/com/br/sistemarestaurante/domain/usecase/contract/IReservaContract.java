package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarReservaRule;

public interface IReservaContract extends IRegistrarReservaRule {

    Reserva validate(IRegistrarReserva iRegistrarReserva, IBuscarRestaurante restaurantRepository, IManterCliente clienteRepositorio, IAttributeValidatorRule<Reserva> reserva) throws SystemException;
}
