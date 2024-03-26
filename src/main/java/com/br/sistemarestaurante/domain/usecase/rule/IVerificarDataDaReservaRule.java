package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.DataPassadaException;

import java.util.Calendar;

public interface IVerificarDataDaReservaRule {
    void verificarDataDaReserva(Reserva reservas) throws DataPassadaException;

    static boolean isValid(Calendar data) throws DataPassadaException {
        resetarHora(data);
        final Calendar dataAtual = resetarHora(Calendar.getInstance());
        if (data.before(dataAtual)) {
            throw new DataPassadaException();
        }
        return true;
    }

    static Calendar resetarHora(Calendar data) {
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        return data;
    }
}
