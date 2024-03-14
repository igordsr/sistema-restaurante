package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;

import java.util.List;

public interface IVerificarDisponibilidadeDaReservaRule {
    boolean verificarDisponibilidadeDaReserva(Restaurante restaurante, List<Reserva> reservas);
}
