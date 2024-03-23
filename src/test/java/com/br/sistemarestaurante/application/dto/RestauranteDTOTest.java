package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestauranteDTOTest {
    @Test
    void testToDomainEntity() {
        UUID id = UUID.randomUUID();
        String nome = "Restaurante Teste";
        String localizacao = "Local Teste";
        String tipoCozinha = "Cozinha Teste";
        LocalTime horarioAbertura = LocalTime.of(9, 0);
        LocalTime horarioFechamento = LocalTime.of(18, 0);
        int capacidade = 4;

        RestauranteDTO restauranteDTO = new RestauranteDTO(id, nome, localizacao, tipoCozinha, horarioAbertura, horarioFechamento, capacidade);

        Restaurante restaurante = restauranteDTO.ToDomainEntity();

        assertEquals(id, restaurante.getIdentificador());
        assertEquals(nome, restaurante.getNome());
        assertEquals(localizacao, restaurante.getLocalizacao());
        assertEquals(tipoCozinha, restaurante.getTipoCozinha());
        assertEquals(horarioAbertura, restaurante.getHorarioAbertura());
        assertEquals(horarioFechamento, restaurante.getHorarioFechamento());
        assertEquals(capacidade, restaurante.getCapacidade());
    }
}

