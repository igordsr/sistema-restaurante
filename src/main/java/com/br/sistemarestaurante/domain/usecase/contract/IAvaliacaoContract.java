package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.usecase.rule.AValidateEntityRole;
import com.br.sistemarestaurante.domain.usecase.rule.IBuscarAvaliacaoRule;
import com.br.sistemarestaurante.domain.usecase.rule.IBuscarRestauranteRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarAvaliacaoRule;

public interface IAvaliacaoContract extends AValidateEntityRole<Avaliacao>, IRegistrarAvaliacaoRule, IBuscarAvaliacaoRule {


}
