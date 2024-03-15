package com.br.sistemarestaurante.domain.servicecontracts;

import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IAlterarStatusReserva;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IBuscarReserva;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IListarReservasRestaurantePeloStatusReserva;
import com.br.sistemarestaurante.domain.servicecontracts.businessrule.IRegistrarReserva;

public interface IManterReserva extends IRegistrarReserva, IListarReservasRestaurantePeloStatusReserva, IAlterarStatusReserva, IBuscarReserva {

}
