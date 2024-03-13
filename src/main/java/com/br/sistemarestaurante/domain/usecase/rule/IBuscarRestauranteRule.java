package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.adapter.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;

import java.util.List;

public interface IBuscarRestauranteRule {

    List<RestauranteTable> findByNomeOrLocalizacaoOrTipoCozinha(IBuscarRestaurante iBuscarRestaurante, RestauranteSearchDTO restauranteSearchDTO);

}
