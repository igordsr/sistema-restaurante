package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.entity.Cliente;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class AvaliacaoTable implements IConverterToDomainEntity<Avaliacao> {
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

    @Override
    public Avaliacao ToDomainEntity() {
        return new Avaliacao(id,reservaTable.getId(),comentario,classificacao);
    }

    public static AvaliacaoTable getInstance(Avaliacao avaliacao, ReservaTable reservaTable) {
        final AvaliacaoTable avaliacaoTable = new AvaliacaoTable();
        avaliacaoTable.setReservaTable(reservaTable);
        avaliacaoTable.setComentario(avaliacao.getComentario());
        avaliacaoTable.setClassificacao(avaliacao.getClassificacao());
        return avaliacaoTable;
    }
}