package com.br.sistemarestaurante.domain.servicecontracts.businessrule;

import com.br.sistemarestaurante.domain.entity.Avaliacao;

public interface IRegistrarAvaliacao {
    Avaliacao registrar(final Avaliacao avaliacao);
}
