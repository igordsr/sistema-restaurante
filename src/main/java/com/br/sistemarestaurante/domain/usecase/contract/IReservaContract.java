package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IManterCliente;
import com.br.sistemarestaurante.domain.servicecontracts.IManterReserva;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarReservaRule;
import com.br.sistemarestaurante.domain.usecase.rule.IReservaAlterarStatusRule;
import com.br.sistemarestaurante.domain.usecase.rule.IVerificarDisponibilidadeDaReservaRule;

public interface IReservaContract extends IRegistrarReservaRule, IReservaAlterarStatusRule, IVerificarDisponibilidadeDaReservaRule {

    Reserva validate(IManterReserva reservaRepository, IBuscarRestaurante restaurantRepository, IManterCliente clienteRepositorio, IAttributeValidatorRule<Reserva> reserva) throws SystemException;
}
