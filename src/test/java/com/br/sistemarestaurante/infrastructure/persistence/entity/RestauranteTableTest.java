package com.br.sistemarestaurante.infrastructure.persistence.entity;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class RestauranteTableTest {
    @Test
    public void testToDomainEntity() {
        RestauranteTable restauranteTable = new RestauranteTable();
        UUID restauranteId = UUID.randomUUID();
        restauranteTable.setId(restauranteId);
        restauranteTable.setNome("Nome do Restaurante");
        restauranteTable.setLocalizacao("Localização do Restaurante");
        restauranteTable.setTipoCozinha("Tipo de Cozinha");
        restauranteTable.setHorarioAbertura(LocalTime.of(8, 0));
        restauranteTable.setHorarioFechamento(LocalTime.of(20, 0));
        restauranteTable.setCapacidade(50);

        Restaurante restaurante = restauranteTable.ToDomainEntity();

        assertEquals(restauranteId, restaurante.getIdentificador());
        assertEquals("Nome do Restaurante", restaurante.getNome());
        assertEquals("Localização do Restaurante", restaurante.getLocalizacao());
        assertEquals("Tipo de Cozinha", restaurante.getTipoCozinha());
        assertEquals(LocalTime.of(8, 0), restaurante.getHorarioAbertura());
        assertEquals(LocalTime.of(20, 0), restaurante.getHorarioFechamento());
    }

    @Test
    public void testGetInstance() {
        Restaurante restaurante = new Restaurante(UUID.randomUUID(), "Nome do Restaurante", "Localização do Restaurante", "Tipo de Cozinha", LocalTime.of(8, 0), LocalTime.of(20, 0), 50);

        RestauranteTable restauranteTable = RestauranteTable.getInstance(restaurante);

        assertEquals("Nome do Restaurante", restauranteTable.getNome());
        assertEquals("Localização do Restaurante", restauranteTable.getLocalizacao());
        assertEquals("Tipo de Cozinha", restauranteTable.getTipoCozinha());
        assertEquals(LocalTime.of(8, 0), restauranteTable.getHorarioAbertura());
        assertEquals(LocalTime.of(20, 0), restauranteTable.getHorarioFechamento());
        assertEquals(50, restauranteTable.getCapacidade());
    }

    @Test
    public void testOneToManyRelationship() {
        ReservaTable reserva1 = mock(ReservaTable.class);
        ReservaTable reserva2 = mock(ReservaTable.class);

        List<ReservaTable> reservas = new ArrayList<>();
        reservas.add(reserva1);
        reservas.add(reserva2);

        RestauranteTable restauranteTable = new RestauranteTable();
        restauranteTable.setReservaTables(reservas);

        assertEquals(reservas.size(), restauranteTable.getReservaTables().size());
        assertEquals(reserva1, restauranteTable.getReservaTables().get(0));
        assertEquals(reserva2, restauranteTable.getReservaTables().get(1));
    }
}
