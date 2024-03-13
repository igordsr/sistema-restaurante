package com.br.sistemarestaurante.domain.servicecontracts;

import com.br.sistemarestaurante.domain.entity.Restaurante;

import java.util.Optional;
import java.util.UUID;

public interface IBuscarRestauranteById {
    Optional<Restaurante> findRestauranteById(final UUID id);
}
