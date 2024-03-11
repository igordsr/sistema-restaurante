package com.br.sistemarestaurante.infrastructure.persistence.repository;

import com.br.sistemarestaurante.infrastructure.persistence.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IReservaRepository extends JpaRepository<Reserva, UUID> {
}
