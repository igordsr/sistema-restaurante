package com.br.sistemarestaurante.domain.usecase;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IManterRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarRestaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class RestauranteUseCaseTest {

    private RestauranteUseCase restauranteUseCase;
    private IManterRestaurante repository;

    private IBuscarRestaurante buscarRestauranteRule;

    @BeforeEach
    public void setup() {
        repository = mock(IManterRestaurante.class);
        buscarRestauranteRule = mock(IBuscarRestaurante.class);
        restauranteUseCase = new RestauranteUseCase(repository);
    }

    @Test
    public void testRegistrarNoRepositorioDeDados() {
        Restaurante restaurante = new Restaurante(UUID.randomUUID(), "Nome", "Loc", "Tipo", LocalTime.of(5, 4, 5), LocalTime.of(15, 4, 5), 5);

        when(repository.resgistar(restaurante)).thenReturn(restaurante);

        Restaurante result = restauranteUseCase.registarNoRepositorioDeDados(restaurante);

        assertNotNull(result);
        verify(repository).resgistar(restaurante);
    }

    @Test
    public void testFindByNomeOrLocalizacaoOrTipoCozinha() {
        List<Restaurante> restaurantes =
                List.of(new Restaurante(UUID.randomUUID(), "Nome", "Loc", "Tipo", LocalTime.of(5, 4, 5), LocalTime.of(15, 4, 5), 5));

        Restaurante restauranteFind = new Restaurante("Nome", "Loc", "Tipo");
        when(repository.findByNomeOrLocalizacaoOrTipoCozinha(restauranteFind)).thenReturn(restaurantes);

        List<Restaurante> result = restauranteUseCase.findByNomeOrLocalizacaoOrTipoCozinha(restauranteFind);

        assertNotNull(result);
        verify(repository).findByNomeOrLocalizacaoOrTipoCozinha(restauranteFind);
    }

}