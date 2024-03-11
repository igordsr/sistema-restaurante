package com.br.sistemarestaurante.adapter.dto;

import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RestauranteDTO(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @NotNull(message = "O Atributo [nome] do Restaurante não pode estar em nulo.")
        @NotBlank(message = "O Atributo [nome] do Restaurante não pode estar em Branco.")
        String nome,
        @NotNull(message = "O Atributo [localizacao] do Restaurante não pode estar em nulo.")
        @NotBlank(message = "O Atributo [localizacao] do Restaurante não pode estar em Branco.")
        String localizacao,
        @NotNull(message = "O Atributo [tipoCozinha] do Restaurante não pode estar em nulo.")
        @NotBlank(message = "O Atributo [tipoCozinha] do Restaurante não pode estar em Branco.")
        String tipoCozinha,
        @NotNull(message = "O Atributo [horariosFuncionamento] do Restaurante não pode estar em nulo.")
        @NotBlank(message = "O Atributo [horariosFuncionamento] do Restaurante não pode estar em Branco.")
        String horariosFuncionamento,
        @NotNull(message = "O Atributo [capacidade] do Restaurante não pode estar em nulo.")
        int capacidade) implements IConverterToDomainEntity<Restaurante> {
    @Override
    public Restaurante ToDomainEntity() {
        return Restaurante.builder()
                .identificador(this.id)
                .nome(this.nome)
                .localizacao(this.localizacao)
                .tipoCozinha(this.tipoCozinha)
                .horariosFuncionamento(this.horariosFuncionamento)
                .capacidade(this.capacidade).build();
    }
}
