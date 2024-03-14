package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IBuscarRestaurante;

import java.util.List;

public interface IBuscarRestauranteRule {

    List<Restaurante> findByNomeOrLocalizacaoOrTipoCozinha(IBuscarRestaurante iBuscarRestaurante, Restaurante restaurante);

}
