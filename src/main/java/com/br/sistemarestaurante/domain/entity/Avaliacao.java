package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;

import java.util.Objects;
import java.util.UUID;

public final class Avaliacao implements IAttributeValidatorRule<Avaliacao> {

    private UUID identificador;
    private UUID reservaId;
    private  String comentario;

    private Integer classificacao;

    public UUID getIdentificador() {
        return identificador;
    }

    public UUID getReservaId() {
        return reservaId;
    }

    public String getComentario() {
        return comentario;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public Avaliacao(UUID identificador, UUID reservaId, String comentario, Integer classificacao) {
        this.identificador = identificador;
        this.reservaId = reservaId;
        this.comentario = comentario;
        this.classificacao = classificacao;
    }

    @Override
    public Avaliacao validate() throws SystemException {
        final String msgException = "[%s] não pode ser nulo ou branco";
        if (Objects.isNull(this.reservaId)) {
            throw new SystemException(String.format(msgException, "reserva Id"));
        } else if (Objects.isNull(this.comentario)) {
            throw new SystemException(String.format(msgException, "comentario"));
        } else if (Objects.isNull(this.classificacao)) {
            throw new SystemException(String.format(msgException, "classificacao"));
        }
        return this;
    }
}
