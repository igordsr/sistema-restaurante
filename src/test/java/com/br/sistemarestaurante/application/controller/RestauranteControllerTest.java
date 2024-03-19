package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.application.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.application.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestauranteControllerTest {
    private RestauranteController restauranteController;

    @Mock
    private RestauranteGateway restauranteGateway;

    RestauranteDTO restauranteDTOMock = mock(RestauranteDTO.class);
    RestauranteSearchDTO restauranteSearchDTOMock = mock(RestauranteSearchDTO.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restauranteController = new RestauranteController(restauranteGateway);
    }

    @Test
    void deveRegistrar() {
        when(restauranteGateway.registrar(restauranteDTOMock)).thenReturn(restauranteDTOMock);

        ResponseEntity<RestauranteDTO> responseEntity = restauranteController.registrar(restauranteDTOMock);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(restauranteDTOMock, responseEntity.getBody());
        verify(restauranteGateway, times(1)).registrar(restauranteDTOMock);
    }

    @Test
    void deveBuscar() {
        List<RestauranteDTO> restaurantes = Collections.singletonList(restauranteDTOMock);

        when(restauranteGateway.buscar(restauranteSearchDTOMock)).thenReturn(restaurantes);

        List<RestauranteDTO> result = restauranteController.buscar(restauranteSearchDTOMock);

        assertEquals(restaurantes, result);
        verify(restauranteGateway, times(1)).buscar(restauranteSearchDTOMock);
    }
}
