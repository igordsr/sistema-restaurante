package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.UUID;

public record RestauranteDTO(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @Schema(example = "HUB Food Art Lounge")
        @NotNull(message = "O Atributo [nome] do Restaurante não pode estar em nulo.")
        @NotBlank(message = "O Atributo [nome] do Restaurante não pode estar em Branco.")
        String nome,
        @Schema(example = "R. Olimpíadas, 205 - Vila Olímpia, São Paulo - SP, 04551-000")
        @NotNull(message = "O Atributo [localizacao] do Restaurante não pode estar em nulo.")
        @NotBlank(message = "O Atributo [localizacao] do Restaurante não pode estar em Branco.")
        String localizacao,

        @Schema(example = "Vegana")
        @NotNull(message = "O Atributo [tipoCozinha] do Restaurante não pode estar em nulo.")
        @NotBlank(message = "O Atributo [tipoCozinha] do Restaurante não pode estar em Branco.")
        String tipoCozinha,
        @Schema(example = "19:00")
        @NotNull(message = "O Atributo [horarioAbertura] do Restaurante não pode estar em nulo.")
        LocalTime horarioAbertura,
        @Schema(example = "23:00")
        @NotNull(message = "O Atributo [horarioFechamento] do Restaurante não pode estar em nulo.")
        LocalTime horarioFechamento,
        @Schema(example = "50")
        @NotNull(message = "O Atributo [capacidade] do Restaurante não pode estar em nulo.")
        int capacidade) implements IConverterToDomainEntity<Restaurante> {
    @Override
    public Restaurante ToDomainEntity() {
        return new Restaurante(id, nome, localizacao, tipoCozinha, horarioAbertura, horarioFechamento, capacidade);
    }
}
