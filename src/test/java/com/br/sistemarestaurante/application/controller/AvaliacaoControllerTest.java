package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import com.br.sistemarestaurante.application.gateway.AvaliacaoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AvaliacaoControllerTest {
    @InjectMocks
    private AvaliacaoController avaliacaoController;

    @Mock
    private AvaliacaoGateway avaliacaoGateway;

    AvaliacaoDTO avaliacaoDTOMock = mock(AvaliacaoDTO.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrar() {
        when(avaliacaoGateway.registrar(avaliacaoDTOMock)).thenReturn(avaliacaoDTOMock);

        ResponseEntity<AvaliacaoDTO> responseEntity = avaliacaoController.registrar(avaliacaoDTOMock);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(avaliacaoDTOMock, responseEntity.getBody());
        verify(avaliacaoGateway, times(1)).registrar(avaliacaoDTOMock);
    }
}
