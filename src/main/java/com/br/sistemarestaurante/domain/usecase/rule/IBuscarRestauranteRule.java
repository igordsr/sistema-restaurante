package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Restaurante;

import java.util.List;

public interface IBuscarRestauranteRule {

    List<Restaurante> findByNomeOrLocalizacaoOrTipoCozinha(Restaurante restaurante);

}
