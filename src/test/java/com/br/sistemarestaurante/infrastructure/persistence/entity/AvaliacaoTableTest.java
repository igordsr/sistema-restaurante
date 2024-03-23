package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AvaliacaoTableTest {

    @Test
    public void testClassificacao() {
        AvaliacaoTable avaliacaoTable = new AvaliacaoTable();

        avaliacaoTable.setClassificacao(4);

        assertEquals(4, avaliacaoTable.getClassificacao());
    }

    @Test
    public void testReservaTableAssignment() {
        ReservaTable reservaTableMock = mock(ReservaTable.class);

        AvaliacaoTable avaliacaoTable = new AvaliacaoTable();

        avaliacaoTable.setReservaTable(reservaTableMock);

        assertEquals(reservaTableMock, avaliacaoTable.getReservaTable());
    }

    @Test
    public void testToDomainEntity() {
        ReservaTable reservaTableMock = mock(ReservaTable.class);
        UUID reservaId = UUID.randomUUID();
        when(reservaTableMock.getId()).thenReturn(reservaId);

        AvaliacaoTable avaliacaoTable = new AvaliacaoTable();
        avaliacaoTable.setId(UUID.randomUUID());
        avaliacaoTable.setReservaTable(reservaTableMock);
        avaliacaoTable.setComentario("Comentario de avaliacao");
        avaliacaoTable.setClassificacao(5);

        Avaliacao avaliacao = avaliacaoTable.ToDomainEntity();

        assertEquals(avaliacaoTable.getId(), avaliacao.getIdentificador());
        assertEquals(reservaId, avaliacao.getReservaId());
        assertEquals(avaliacaoTable.getComentario(), avaliacao.getComentario());
    }

    @Test
    public void testGetInstance() {
        Avaliacao avaliacao = new Avaliacao(UUID.randomUUID(), UUID.randomUUID(), "Comentario", 4);
      
        AvaliacaoTable avaliacaoTable = AvaliacaoTable.getInstance(avaliacao);

        assertEquals(avaliacao.getComentario(), avaliacaoTable.getComentario());
    }
}
