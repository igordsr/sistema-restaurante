package com.br.sistemarestaurante.domain.servicecontracts.businessrule;

import com.br.sistemarestaurante.domain.entity.Avaliacao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBuscarAvaliacao {
    Optional<Avaliacao> findAvaliacaoById(final UUID id);
    List<Avaliacao> findByReservaId(UUID id);
}
