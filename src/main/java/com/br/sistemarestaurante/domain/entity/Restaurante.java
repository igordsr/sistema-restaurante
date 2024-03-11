package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.RestauranteException;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidator;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;


public final class Restaurante implements IAttributeValidator<Restaurante> {
    private UUID identificador;
    private String nome;
    private String localizacao;
    private String tipoCozinha;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private Integer capacidade;

    public Restaurante(UUID identificador, String nome, String localizacao, String tipoCozinha, LocalTime horarioAbertura, LocalTime horarioFechamento, Integer capacidade) {
        this.identificador = identificador;
        this.nome = nome;
        this.localizacao = localizacao;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.capacidade = capacidade;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    @Override
    public Restaurante validate() throws RestauranteException {
        final String msgException = "[%s] não pode ser nulo ou branco";
        if (Objects.isNull(this.nome)) {
            throw new RestauranteException(String.format(msgException, "nome"));
        } else if (Objects.isNull(this.localizacao)) {
            throw new RestauranteException(String.format(msgException, "localização"));
        } else if (Objects.isNull(this.tipoCozinha)) {
            throw new RestauranteException(String.format(msgException, "tipoCozinha"));
        } else if (Objects.isNull(this.horarioAbertura)) {
            throw new RestauranteException(String.format(msgException, "horarioAbertura"));
        } else if (Objects.isNull(this.horarioFechamento)) {
            throw new RestauranteException(String.format(msgException, "horarioFechamento"));
        } else if (Objects.isNull(this.capacidade)) {
            throw new RestauranteException(String.format(msgException, "capacidade"));
        }
        return this;
    }
}
