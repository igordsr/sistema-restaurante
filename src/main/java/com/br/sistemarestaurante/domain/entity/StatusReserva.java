package com.br.sistemarestaurante.domain.entity;

public enum StatusReserva {
    RESERVADO("reservado"),
    CANCELADO("cancelado"),
    CONCLUIDO("concluido");

    private String descricao;

    StatusReserva(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
