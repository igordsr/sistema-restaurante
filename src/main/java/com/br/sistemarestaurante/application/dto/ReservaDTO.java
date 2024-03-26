package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.annotation.NotPastDate;
import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

public record ReservaDTO(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @NotNull(message = "O Atributo [restauranteId] da Reserva não pode estar em nulo.")
        UUID restauranteId,
        @Schema(example = "Isis Sueli Heloise dos Santos")
        @NotNull(message = "O Atributo [nomeCliente] da Reserva não pode estar em nulo.")
        @NotBlank(message = "O Atributo [nomeCliente] da Reserva não pode estar em Branco.")
        String nomeCliente,

        @Schema(example = "isis_dossantos@eximiart.com.br")
        @Email(message = "E-mail inválido")
        @NotNull(message = "O Atributo [emailCliente] da Reserva não pode estar em nulo.")
        @NotBlank(message = "O Atributo [emailCliente] da Reserva não pode estar em Branco.")
        String emailCliente,
        @Schema(example = "(21) 98795-3698")
        @NotNull(message = "O Atributo [telefoneCliente] da Reserva não pode estar em nulo.")
        @NotBlank(message = "O Atributo [telefoneCliente] da Reserva não pode estar em Branco.")
        String telefoneCliente,
        @NotPastDate
        @NotNull(message = "O Atributo [data] da Reserva não pode estar em nulo.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Calendar data,
        @NotNull(message = "O Atributo [hora] da Reserva não pode estar em nulo.")
        @DateTimeFormat(pattern = "HH:mm")
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
