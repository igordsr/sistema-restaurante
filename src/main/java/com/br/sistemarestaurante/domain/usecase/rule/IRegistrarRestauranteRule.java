package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarRestaurante;

public interface IRegistrarRestauranteRule {
    Restaurante registarNoRepositorioDeDados(final IRegistrarRestaurante iRegistrarRestaurante, final Restaurante restaurante);
}
