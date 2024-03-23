package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public final class Reserva implements IAttributeValidatorRule<Reserva> {
    private UUID identificador;
    private UUID restauranteId;
    private Cliente cliente;
    private Calendar data;
    private LocalTime hora;
    private StatusReserva status;

    public Reserva(UUID identificador, UUID restauranteId, Cliente cliente, Calendar data, LocalTime hora) {
        this.identificador = identificador;
        this.restauranteId = restauranteId;
        this.cliente = cliente;
        this.data = data;
        this.hora = hora;
        this.status = StatusReserva.RESERVADO;
    }

    public Reserva(UUID identificador, UUID restauranteId, Cliente cliente, Calendar data, LocalTime hora, StatusReserva status) {
        this.identificador = identificador;
        this.restauranteId = restauranteId;
        this.cliente = cliente;
        this.data = data;
        this.hora = hora;
        this.status = status;
    }

    public Reserva(UUID identificador, StatusReserva status) {
        this.identificador = identificador;
        this.status = status;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public UUID getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(UUID restauranteId) {
        this.restauranteId = restauranteId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    @Override
    public Reserva validate() throws SystemException {
        final String msgException = "[%s] não pode ser nulo ou branco";
        if (Objects.isNull(this.restauranteId)) {
            throw new SystemException(String.format(msgException, "restaurante"));
        } else if (Objects.isNull(this.cliente)) {
            throw new SystemException(String.format(msgException, "cliente"));
        } else if (Objects.isNull(this.data)) {
            throw new SystemException(String.format(msgException, "data"));
        } else if (Objects.isNull(this.hora)) {
            throw new SystemException(String.format(msgException, "hora"));
        }
        return this;
    }
}
