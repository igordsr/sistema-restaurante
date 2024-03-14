package com.br.sistemarestaurante.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class AvaliacaoTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "reserva_id")
    private ReservaTable reservaTable;

    @Column(nullable = false)
    private String comentario;

    @Column(nullable = false)
    private int classificacao;
}