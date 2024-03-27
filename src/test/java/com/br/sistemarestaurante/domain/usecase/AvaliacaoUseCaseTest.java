package com.br.sistemarestaurante.domain.usecase;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.exception.SystemException;
import com.br.sistemarestaurante.domain.servicecontracts.IManterAvaliacao;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarAvaliacao;
import com.br.sistemarestaurante.domain.usecase.rule.IAttributeValidatorRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AvaliacaoUseCaseTest {

    private AvaliacaoUseCase avaliacaoUseCase;
    private IManterAvaliacao repository;
    private IAttributeValidatorRule<Avaliacao> validatorRule;
    private IBuscarAvaliacao buscarAvaliacaoRule;

    @BeforeEach
    void setup() {
        repository = mock(IManterAvaliacao.class);
        validatorRule = mock(IAttributeValidatorRule.class);
        buscarAvaliacaoRule = mock(IBuscarAvaliacao.class);
        avaliacaoUseCase = new AvaliacaoUseCase(repository);
    }

    @Test
    void testRegistarNoRepositorioDeDados() {
        Avaliacao avaliacao = new Avaliacao(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "bom",
                4
        );
        when(repository.registrar(avaliacao)).thenReturn(avaliacao);

        Avaliacao result = avaliacaoUseCase.registarNoRepositorioDeDados(avaliacao);

        assertNotNull(result);
        verify(repository).registrar(avaliacao);
    }

    @Test
    void testValidate() throws SystemException {
        Avaliacao avaliacao = new Avaliacao(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "bom",
                4
        );


        try (MockedStatic<IAttributeValidatorRule> mockedStatic = Mockito.mockStatic(IAttributeValidatorRule.class)) {
            mockedStatic.when(() -> IAttributeValidatorRule.validar(any())).thenReturn(avaliacao);

            AvaliacaoUseCase avaliacaoUseCase = new AvaliacaoUseCase(null); //
            Avaliacao result = avaliacaoUseCase.validate(avaliacao);

            Assertions.assertNotNull(result);

            mockedStatic.verify(() -> IAttributeValidatorRule.validar(avaliacao));
        }
    }

    @Test
    void testBuscarAvaliacaoPorReserva() {
        UUID reservaId = UUID.randomUUID();
        List<Avaliacao> avaliacoes = List.of((new Avaliacao(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "bom",
                4
        )));
        when(buscarAvaliacaoRule.findByReservaTable(reservaId)).thenReturn(avaliacoes);

        List<Avaliacao> result = avaliacaoUseCase.buscarAvaliacaoPorReserva(buscarAvaliacaoRule, reservaId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(buscarAvaliacaoRule).findByReservaTable(reservaId);
    }

}