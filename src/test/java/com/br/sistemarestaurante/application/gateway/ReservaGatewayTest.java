package com.br.sistemarestaurante.application.gateway;

import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.application.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IRegistrarRestaurante;
import com.br.sistemarestaurante.infrastructure.persistence.service.RestauranteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestauranteGatewayTest {

    @Mock
    private RestauranteService restauranteServiceMock;

    @InjectMocks
    private RestauranteGateway restauranteGateway;

    @Mock
    IRegistrarRestaurante iRegistrarRestaurante;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrarRestaurante() {
        Restaurante restaurante = new Restaurante(UUID.randomUUID(), "Nome", "Localização", "Cozinha", LocalTime.of(8, 0), LocalTime.of(20, 0), 50);
        IConverterToDomainEntity<Restaurante> obj = () -> restaurante;

        when(restauranteServiceMock.resgistar(any(Restaurante.class))).thenReturn(restaurante);

        RestauranteDTO restauranteDTO = restauranteGateway.registrar(obj);

        assertEquals(restaurante.getIdentificador(), restauranteDTO.id());
        assertEquals(restaurante.getNome(), restauranteDTO.nome());
        assertEquals(restaurante.getLocalizacao(), restauranteDTO.localizacao());
        assertEquals(restaurante.getTipoCozinha(), restauranteDTO.tipoCozinha());
        assertEquals(restaurante.getHorarioAbertura(), restauranteDTO.horarioAbertura());
        assertEquals(restaurante.getHorarioFechamento(), restauranteDTO.horarioFechamento());
        assertEquals(restaurante.getCapacidade(), restauranteDTO.capacidade());

        verify(restauranteServiceMock, times(1)).resgistar(any(Restaurante.class));
    }

    @Test
    public void testBuscarRestaurantes() {
        RestauranteSearchDTO searchDTO = new RestauranteSearchDTO("Nome", "Localização", "Cozinha");
        Restaurante restaurante = new Restaurante(UUID.randomUUID(), "Nome", "Localização", "Cozinha", LocalTime.of(8, 0), LocalTime.of(20, 0), 50);
        List<Restaurante> restaurantes = Collections.singletonList(restaurante);

        when(restauranteServiceMock.findByNomeOrLocalizacaoOrTipoCozinha(any(Restaurante.class))).thenReturn(restaurantes);

        List<RestauranteDTO> restauranteDTOs = restauranteGateway.buscar(searchDTO);

        assertEquals(1, restauranteDTOs.size());
        RestauranteDTO restauranteDTO = restauranteDTOs.get(0);
        assertEquals(restaurante.getIdentificador(), restauranteDTO.id());
        assertEquals(restaurante.getNome(), restauranteDTO.nome());
        assertEquals(restaurante.getLocalizacao(), restauranteDTO.localizacao());
        assertEquals(restaurante.getTipoCozinha(), restauranteDTO.tipoCozinha());
        assertEquals(restaurante.getHorarioAbertura(), restauranteDTO.horarioAbertura());
        assertEquals(restaurante.getHorarioFechamento(), restauranteDTO.horarioFechamento());
        assertEquals(restaurante.getCapacidade(), restauranteDTO.capacidade());

        verify(restauranteServiceMock, times(1)).findByNomeOrLocalizacaoOrTipoCozinha(any(Restaurante.class));
    }
}
