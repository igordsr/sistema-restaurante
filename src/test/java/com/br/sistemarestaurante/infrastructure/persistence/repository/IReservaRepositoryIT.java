package com.br.sistemarestaurante.infrastructure.persistence.repository;

import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class IReservaRepositoryIT {

    @Autowired
    IReservaRepository iReservaRepository;

    @Test
    void devePermitirCriarTabela() {
        var totalRegistros = iReservaRepository.count();
        assertThat(totalRegistros).isGreaterThan(0);
    }

    @Test
    void devePermitirBuscarPorIdRestauranteFound() {
        var encontrado = iReservaRepository.findByRestauranteId(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"));

        assertThat(encontrado.size()).isEqualTo(2);

        assertThat(encontrado.get(0).getHora().getHour()).isEqualTo(19);
        assertThat(encontrado.get(0).getHora().getMinute()).isEqualTo(0);
        assertThat(encontrado.get(0).getStatus()).isEqualTo(StatusReserva.RESERVADO);
    }

    @Test
    void devePermitirBuscarPorIdRestauranteNotFound() {
        var encontrado = iReservaRepository.findByRestauranteId(UUID.fromString("555e555-e55b-55d3-a555-555555555000"));

        assertThat(encontrado.size()).isEqualTo(0);
    }

    @Test
    void devePermitirBuscarPorRestauranteDataHoraStatus() {
        Calendar data = Calendar.getInstance();
        data.set(2024, 04, 25);

        RestauranteTable restauranteTable = new RestauranteTable();
        restauranteTable.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"));

        var encontrado = iReservaRepository.findByRestauranteAndDataAndHoraAndStatus(restauranteTable, data, LocalTime.of(16, 0, 0), StatusReserva.RESERVADO);

        assertThat(encontrado.size()).isEqualTo(0);
    }
}
