package com.br.sistemarestaurante.infrastructure.persistence.repository;

import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteTable, UUID> {

    Optional<ClienteTable> findByEmail(String email);
}
