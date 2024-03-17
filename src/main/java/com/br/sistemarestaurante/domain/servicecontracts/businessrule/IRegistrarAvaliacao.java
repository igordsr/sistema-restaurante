package com.br.sistemarestaurante.domain.servicecontracts.businessrule;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.entity.Reserva;

public interface IRegistrarAvaliacao {
    Avaliacao registrar(final Avaliacao avaliacao);
}
