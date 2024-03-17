package com.br.sistemarestaurante.domain.servicecontracts;

import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarAvaliacao;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarRestaurante;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IRegistrarAvaliacao;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IRegistrarRestaurante;

public interface IManterAvaliacao extends IRegistrarAvaliacao, IBuscarAvaliacao {

}
