package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AvaliacaoDTO(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @NotNull(message = "O Atributo [reservaId] da Avaliação não pode estar em nulo.")
        UUID reservaId,
        @NotNull(message = "O Atributo [comentario] da Avaliação não pode estar em nulo.")
        @NotBlank(message = "O Atributo [comentario] da Avaliação não pode estar em Branco.")
        String comentario,
        @NotNull(message = "O Atributo [classificacao] da Avaliação não pode estar em nulo.")
        int classificacao) implements IConverterToDomainEntity<Avaliacao> {
    @Override
    public Avaliacao ToDomainEntity() {
        return new Avaliacao(id, reservaId, comentario, classificacao);
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public UUID reservaId() {
        return reservaId;
    }

    @Override
    public String comentario() {
        return comentario;
    }

    @Override
    public int classificacao() {
        return classificacao;
    }
}
