package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.HorarioNoPassadoException;
import com.br.sistemarestaurante.domain.exception.RestauranteFechadoException;
import com.br.sistemarestaurante.domain.exception.SystemException;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Calendar;

public interface IVerificarHoraDaReservaDoRestauranteRule {
    void verificarHoraDaReservaEDoRestaurante(Reserva reserva, Restaurante restaurante) throws RestauranteFechadoException;

    static void validarHorario(Reserva reserva, Restaurante restaurante) throws SystemException {
        IVerificarDataDaReservaRule.isValid(reserva.getData());
        validarHoraEDataAtual(reserva.getData(), reserva.getHora());
        isValid(reserva.getHora(), restaurante.getHorarioAbertura(), restaurante.getHorarioFechamento());
    }

    private static void validarHoraEDataAtual(Calendar date, LocalTime hora) throws SystemException {
        Calendar today = IVerificarDataDaReservaRule.resetarHora(Calendar.getInstance());
        final LocalTime now = getCurrentDate();
        if (!date.equals(today)) {
            return;
        }

        if (hora.isBefore(now)) {
            throw new HorarioNoPassadoException(hora);
        }
    }

    static void isValid(LocalTime hora, LocalTime horarioAbertura, LocalTime horarioFechamento) throws RestauranteFechadoException {
        // Se o horário de abertura é antes do horário de fechamento, estamos no período diurno
        if (horarioAbertura.isBefore(horarioFechamento)) {
            // Se a hora da reserva não estiver entre o horário de abertura e fechamento, o restaurante está fechado
            if (hora.isBefore(horarioAbertura) || hora.isAfter(horarioFechamento)) {
                throw new RestauranteFechadoException(hora, horarioAbertura, horarioFechamento);
            }
        }
        // Se o horário de fechamento é antes do horário de abertura, estamos no período noturno
        else {
            // Se a hora da reserva estiver fora do intervalo de abertura e fechamento, o restaurante está fechado
            if (hora.isBefore(horarioAbertura) && hora.isAfter(horarioFechamento)) {
                throw new RestauranteFechadoException(hora, horarioAbertura, horarioFechamento);
            }
        }
    }

    static LocalTime getCurrentDate(){
        return LocalTime.from(ZonedDateTime.now());
    }
}
