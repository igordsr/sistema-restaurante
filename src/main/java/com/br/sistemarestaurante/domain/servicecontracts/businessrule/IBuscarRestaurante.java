package com.br.sistemarestaurante.domain.servicecontracts.businessrule;

import com.br.sistemarestaurante.domain.entity.Restaurante;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBuscarRestaurante {
    Optional<Restaurante> findRestauranteById(final UUID id);
    List<Restaurante> findByNomeOrLocalizacaoOrTipoCozinha(Restaurante restaurante);
}
