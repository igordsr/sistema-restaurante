package com.br.sistemarestaurante.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestauranteSearchDTOTest {

    @Test
    void testConstructor() {
        String nome = "Restaurante A";
        String localizacao = "Local A";
        String tipoCozinha = "Cozinha A";

        RestauranteSearchDTO restauranteSearchDTO = new RestauranteSearchDTO(nome, localizacao, tipoCozinha);

        assertEquals(nome, restauranteSearchDTO.nome());
        assertEquals(localizacao, restauranteSearchDTO.localizacao());
        assertEquals(tipoCozinha, restauranteSearchDTO.tipoCozinha());
    }
}
