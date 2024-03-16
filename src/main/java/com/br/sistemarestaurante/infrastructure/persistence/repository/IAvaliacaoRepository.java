package com.br.sistemarestaurante.infrastructure.persistence.repository;

import com.br.sistemarestaurante.infrastructure.persistence.entity.AvaliacaoTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAvaliacaoRepository extends JpaRepository<AvaliacaoTable, UUID> {

    List<AvaliacaoTable> findByReservaId(UUID reservaId);
}
