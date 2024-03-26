package com.br.sistemarestaurante.utils;

import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

public class InstanceGeneratorHelper {

    public static Restaurante gerarRestaurante() {
        return new Restaurante(UUID.randomUUID(),
                "Sal Gastronomia",
                "R. Bela Cintra, 1958",
                "Brasileira",
                LocalTime.of(5, 4, 5),
                LocalTime.of(15, 4, 5),
                5);
    }


    public static Cliente gerarCliente() {
        return new Cliente(UUID.randomUUID(),
                "Rodrigo Leonardo Yago da Paz",
                "rodrigo.leonardo.dapaz@andrediaz.com",
                "(68) 98876-9207"
        );
    }

    public static Reserva gerarReserva() {
        Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.DAY_OF_MONTH, 1);
        return new Reserva(UUID.randomUUID(),
                gerarRestaurante().getIdentificador(),
                gerarCliente(),
                futureDate,
                LocalTime.of(11, 30, 5));
    }
}
