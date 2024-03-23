package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AvaliacaoDTOTest {
    private int classificacao;

    @Test
    void testToDomainEntity() {
        UUID id = UUID.randomUUID();
        UUID reservaId = UUID.randomUUID();
        String comentario = "Excelente serviço!";
        int classificacao = 5;

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(id, reservaId, comentario, classificacao);

        Avaliacao avaliacao = avaliacaoDTO.ToDomainEntity();

        assertEquals(id, avaliacao.getIdentificador());
        assertEquals(reservaId, avaliacao.getReservaId());
        assertEquals(comentario, avaliacao.getComentario());
        assertEquals(classificacao, avaliacao.getClassificacao());
    }
}
