package com.br.sistemarestaurante.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class ReservaTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteTable restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteTable clienteTable;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String hora;

    private String status;

    @OneToOne(mappedBy = "reservaTable")
    private AvaliacaoTable avaliacaoTable;
}