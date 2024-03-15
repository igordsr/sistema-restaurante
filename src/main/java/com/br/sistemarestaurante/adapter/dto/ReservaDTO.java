package com.br.sistemarestaurante.adapter.dto;

import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.annotation.NotPastDate;
import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

public record ReservaDTO(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @NotNull(message = "O Atributo [restauranteId] da Reserva não pode estar em nulo.")
        UUID restauranteId,

        @NotNull(message = "O Atributo [nomeCliente] da Reserva não pode estar em nulo.")
        @NotBlank(message = "O Atributo [nomeCliente] da Reserva não pode estar em Branco.")
        String nomeCliente,

        @Email(message = "E-mail inválido")
        @NotNull(message = "O Atributo [emailCliente] da Reserva não pode estar em nulo.")
        @NotBlank(message = "O Atributo [emailCliente] da Reserva não pode estar em Branco.")
        String emailCliente,

        @NotNull(message = "O Atributo [telefoneCliente] da Reserva não pode estar em nulo.")
        @NotBlank(message = "O Atributo [telefoneCliente] da Reserva não pode estar em Branco.")
        String telefoneCliente,
        @NotPastDate
        @NotNull(message = "O Atributo [data] da Reserva não pode estar em nulo.")
        Calendar data,

        @NotNull(message = "O Atributo [hora] da Reserva não pode estar em nulo.")
        LocalTime hora,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        StatusReserva status

) implements IConverterToDomainEntity<Reserva> {
    @Override
    public Reserva ToDomainEntity() {
        Cliente cliente = new Cliente(null, nomeCliente, emailCliente, telefoneCliente);
        return new Reserva(id, restauranteId, cliente, data, hora);
    }
}
