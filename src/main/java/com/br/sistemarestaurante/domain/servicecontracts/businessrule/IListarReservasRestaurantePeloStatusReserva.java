package com.br.sistemarestaurante.domain.servicecontracts.businessrule;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

public interface IListarReservasRestaurantePeloStatusReserva {
    List<Reserva> listarReservasRestaurantePeloStatusReserva(final Restaurante restaurante, Calendar data, LocalTime hora, final StatusReserva statusReserva);
}
