package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "restaurantes")
public class RestauranteTable implements IConverterToDomainEntity<Restaurante> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private String tipoCozinha;

    @Column(nullable = false)
    private String horariosFuncionamento;

    @Column(nullable = false)
    private int capacidade;

    @OneToMany(mappedBy = "restaurante")
    private List<Reserva> reservas;

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