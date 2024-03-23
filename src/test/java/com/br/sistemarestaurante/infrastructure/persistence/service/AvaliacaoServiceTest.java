package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.infrastructure.persistence.entity.AvaliacaoTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IAvaliacaoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AvaliacaoServiceTest {

    @Mock
    private IAvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Mock
    private ReservaService reservaService;

    @Mock
    private AvaliacaoTable avaliacaoTableMock;

    @Mock
    private Avaliacao avaliacaoMock;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        when(avaliacaoTableMock.ToDomainEntity()).thenReturn(avaliacaoMock);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveBuscarAvaliacaoPorId() {
        UUID idTest = mock(UUID.class);
        avaliacaoTableMock.setId(idTest);

        when(avaliacaoRepository.findById(any())).thenReturn(Optional.of(avaliacaoTableMock));

        Optional<Avaliacao> avaliacaoEncontrada = avaliacaoService.findAvaliacaoById(idTest);

        assertThat(avaliacaoEncontrada).isNotNull().isEqualTo(Optional.of(avaliacaoMock));
        verify(avaliacaoRepository, times(1)).findById(any());
    }

    @Test
    void deveBuscarAvaliacoesPorReservaId() {
        UUID reservaIdTest = UUID.randomUUID();
        ReservaTable reservaTableMock = mock(ReservaTable.class);

        when(reservaService.buscarReservaTablePorId(reservaIdTest)).thenReturn(Optional.of(reservaTableMock));
        when(avaliacaoRepository.findByReservaTable(reservaTableMock)).thenReturn(List.of(avaliacaoTableMock));
        when(avaliacaoTableMock.ToDomainEntity()).thenReturn(avaliacaoMock);

        List<Avaliacao> avaliacoesEncontradas = avaliacaoService.findByReservaTable(reservaIdTest);

        assertThat(avaliacoesEncontradas).isNotNull().hasSize(1).contains(avaliacaoMock);
        verify(avaliacaoRepository, times(1)).findByReservaTable(reservaTableMock);
    }


}