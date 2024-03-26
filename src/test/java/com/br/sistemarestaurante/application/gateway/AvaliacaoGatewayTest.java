package com.br.sistemarestaurante.application.gateway;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.infrastructure.persistence.service.AvaliacaoService;
import com.br.sistemarestaurante.application.gateway.AvaliacaoGateway;
import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AvaliacaoGatewayTest {

    @Mock
    private AvaliacaoService avaliacaoServiceMock;

    @InjectMocks
    private AvaliacaoGateway avaliacaoGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrarAvaliacao() {
        Avaliacao avaliacao = new Avaliacao(UUID.randomUUID(), UUID.randomUUID(), "Comentario teste", 5);
        IConverterToDomainEntity<Avaliacao> obj = () -> avaliacao;

        when(avaliacaoServiceMock.registrar(any(Avaliacao.class))).thenReturn(avaliacao);

        AvaliacaoDTO avaliacaoDTO = avaliacaoGateway.registrar(obj);

        assertEquals(avaliacao.getIdentificador(), avaliacaoDTO.id());
        assertEquals(avaliacao.getReservaId(), avaliacaoDTO.reservaId());
        assertEquals(avaliacao.getComentario(), avaliacaoDTO.comentario());
        assertEquals(avaliacao.getClassificacao(), avaliacaoDTO.classificacao());

        verify(avaliacaoServiceMock, times(1)).registrar(any(Avaliacao.class));
    }

    @Test
    public void testBuscarAvaliacoesPorReserva() {
        UUID reservaId = UUID.randomUUID();
        Avaliacao avaliacao = new Avaliacao(UUID.randomUUID(), reservaId, "Comentario teste", 5);
        List<Avaliacao> avaliacoes = Collections.singletonList(avaliacao);

        when(avaliacaoServiceMock.findByReservaTable(any(UUID.class))).thenReturn(avaliacoes);

        List<AvaliacaoDTO> avaliacaoDTOs = avaliacaoGateway.buscar(reservaId);

        assertEquals(1, avaliacaoDTOs.size());
        AvaliacaoDTO avaliacaoDTO = avaliacaoDTOs.get(0);
        assertEquals(avaliacao.getIdentificador(), avaliacaoDTO.id());
        assertEquals(avaliacao.getReservaId(), avaliacaoDTO.reservaId());
        assertEquals(avaliacao.getComentario(), avaliacaoDTO.comentario());
        assertEquals(avaliacao.getClassificacao(), avaliacaoDTO.classificacao());

        verify(avaliacaoServiceMock, times(1)).findByReservaTable(any(UUID.class));
    }
}