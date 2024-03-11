package com.br.sistemarestaurante.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteTable restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String hora;

    private String status;

    @OneToOne(mappedBy = "reserva")
    private Avaliacao avaliacao;
}