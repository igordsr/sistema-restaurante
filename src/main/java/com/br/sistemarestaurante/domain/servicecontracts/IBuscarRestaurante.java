package com.br.sistemarestaurante.domain.servicecontracts;

import com.br.sistemarestaurante.adapter.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBuscarRestaurante {
    Optional<Restaurante> findRestauranteById(final UUID id);

    List<RestauranteTable> findByNomeOrLocalizacaoOrTipoCozinha(RestauranteSearchDTO restauranteSearchDTO);
}
