package com.br.sistemarestaurante.infrastructure.persistence.repository;

import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
@Repository
public interface IReservaRepository extends JpaRepository<ReservaTable, UUID> {
    List<ReservaTable> findByRestauranteAndDataAndHoraAndStatus(RestauranteTable restaurante, Calendar data, LocalTime hora, StatusReserva status);
}
