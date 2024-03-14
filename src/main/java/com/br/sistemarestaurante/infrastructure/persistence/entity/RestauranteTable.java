package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
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
    private LocalTime horarioAbertura;

    @Column(nullable = false)
    private LocalTime horarioFechamento;

    @Column(nullable = false)
    private int capacidade;

    @OneToMany(mappedBy = "restaurante")
    private List<ReservaTable> reservaTables;

    @Override
    public Restaurante ToDomainEntity() {
        return new Restaurante(id, nome, localizacao, tipoCozinha, horarioAbertura, horarioFechamento, capacidade);
    }

    public static RestauranteTable getInstance(Restaurante restauranteDomain) {
        final RestauranteTable restauranteTable = new RestauranteTable();
        restauranteTable.setId(restauranteDomain.getIdentificador());
        restauranteTable.setNome(restauranteDomain.getNome());
        restauranteTable.setLocalizacao(restauranteDomain.getLocalizacao());
        restauranteTable.setTipoCozinha(restauranteDomain.getTipoCozinha());
        restauranteTable.setHorarioAbertura(restauranteDomain.getHorarioAbertura());
        restauranteTable.setHorarioFechamento(restauranteDomain.getHorarioFechamento());
        restauranteTable.setCapacidade(restauranteDomain.getCapacidade());
        return restauranteTable;
    }
}