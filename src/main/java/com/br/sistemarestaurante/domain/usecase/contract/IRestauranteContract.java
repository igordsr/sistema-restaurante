package com.br.sistemarestaurante.domain.usecase.contract;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.usecase.rule.AValidateEntityRole;
import com.br.sistemarestaurante.domain.usecase.rule.IBuscarRestauranteRule;
import com.br.sistemarestaurante.domain.usecase.rule.IRegistrarRestauranteRule;

public interface IRestauranteContract extends AValidateEntityRole<Restaurante>, IRegistrarRestauranteRule, IBuscarRestauranteRule {


}
