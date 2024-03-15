package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.ReservaNaoDisponivelException;

import java.util.List;

public interface IVerificarDisponibilidadeDaReservaRule {
    void verificarDisponibilidadeDaReserva(Restaurante restaurante, List<Reserva> reservas);

    static void validarDisponibilidadeDaReserva(Restaurante restaurante, List<Reserva> reservas) throws ReservaNaoDisponivelException {
        final int mesasDisponiveis = restaurante.getCapacidade() - reservas.size();
        if (mesasDisponiveis <= 0) {
            throw new ReservaNaoDisponivelException();
        }
    }
}
