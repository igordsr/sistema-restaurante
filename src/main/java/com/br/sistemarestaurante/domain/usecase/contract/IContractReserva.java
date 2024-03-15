package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.usecase.rule.*;

public interface IContractReserva extends AValidateEntityRole<Reserva>, IRegistrarReservaRule, IAlterarStatusReservaRule, IVerificarDisponibilidadeDaReservaRule, IVerificarDataDaReservaRule, IVerificarHoraDaReservaDoRestauranteRule {

}
