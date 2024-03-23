package com.br.sistemarestaurante.domain.usecase.rule;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarAvaliacao;
import java.util.List;
import java.util.UUID;

public interface IBuscarAvaliacaoRule {

    List<Avaliacao> buscarAvaliacaoPorReserva(IBuscarAvaliacao repository, UUID reservaId);


}
