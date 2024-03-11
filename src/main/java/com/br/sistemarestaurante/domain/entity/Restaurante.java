package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.infrastructure.persistence.entity.Reserva;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public final class Restaurante {
    private UUID identificador;
    private String nome;
    private String localizacao;
    private String tipoCozinha;
    private String horariosFuncionamento;
    private int capacidade;
}
