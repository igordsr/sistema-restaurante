package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IRestauranteRepository;
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

class RestauranteServiceTest {

    @Mock
    private IRestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteService restauranteService;

    Restaurante restauranteMock = mock(Restaurante.class);
    RestauranteTable restauranteTableMock = mock(RestauranteTable.class);
    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        when(restauranteTableMock.ToDomainEntity()).thenReturn(restauranteMock);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRegistrar() {
        when(restauranteRepository.save(any())).thenReturn(restauranteTableMock);

        Restaurante restauranteRegistrado = restauranteService.resgistar(restauranteMock);

        assertThat(restauranteRegistrado).isNotNull().isEqualTo(restauranteMock);
        verify(restauranteRepository, times(1)).save(any());
    }

    @Test
    void deveBuscarRestaurantePorId() {
        UUID idTest = mock(UUID.class);
        restauranteTableMock.setId(idTest);

        when(restauranteRepository.findById(any())).thenReturn(Optional.of(restauranteTableMock));

        Optional<Restaurante> restauranteEncontrado = restauranteService.findRestauranteById(idTest);

        assertThat(restauranteEncontrado).isNotNull().isEqualTo(Optional.of(restauranteMock));
        verify(restauranteRepository, times(1)).findById(any());
    }

    @Test
    void deveBuscarRestaurantePorNomeOrLocalizacaoOrTipoCozinha() {
        when(restauranteRepository.findByNomeContainingAndLocalizacaoContainingAndTipoCozinhaContaining(any(), any(), any())).thenReturn(List.of(restauranteTableMock));

        List<Restaurante> restauranteEncontrado = restauranteService.findByNomeOrLocalizacaoOrTipoCozinha(restauranteMock);

        assertThat(restauranteEncontrado).isNotNull().isEqualTo(List.of(restauranteMock));
        verify(restauranteRepository, times(1)).findByNomeContainingAndLocalizacaoContainingAndTipoCozinhaContaining(any(), any(), any());
    }
}
