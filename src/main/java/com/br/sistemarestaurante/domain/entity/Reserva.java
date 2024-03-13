package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.ReservaException;
import com.br.sistemarestaurante.domain.exception.RestauranteException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidator;

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public final class Reserva implements IAttributeValidator<Reserva> {
    private UUID identificador;
    private UUID restauranteId;
    private Cliente cliente;
    private Calendar data;
    private String hora;
    private StatusReserva status;

    public Reserva(UUID identificador, UUID restauranteId, Cliente cliente, Calendar data, String hora) {
        this.identificador = identificador;
        this.restauranteId = restauranteId;
        this.cliente = cliente;
        this.data = data;
        this.hora = hora;
        this.status = StatusReserva.RESERVADO;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public UUID getRestauranteId() {
        return restauranteId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Calendar getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public StatusReserva getStatus() {
        return status;
    }

    @Override
    public Reserva validate() throws ReservaException {
        final String msgException = "[%s] não pode ser nulo ou branco";
        if (Objects.isNull(this.restauranteId)) {
            throw new RestauranteException(String.format(msgException, "restaurante"));
        } else if (Objects.isNull(this.cliente)) {
            throw new RestauranteException(String.format(msgException, "cliente"));
        } else if (Objects.isNull(this.data)) {
            throw new RestauranteException(String.format(msgException, "data"));
        } else if (Objects.isNull(this.hora)) {
            throw new RestauranteException(String.format(msgException, "hora"));
        }
        return this;
    }
}
