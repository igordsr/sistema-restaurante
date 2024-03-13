package com.br.sistemarestaurante.infrastructure.persistence.usecase;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaRepositoryImpl implements IRegistrarReserva {
    private final IReservaRepository repository;
    private final RestauranteRepositoryImpl restauranteRepository;
    private final ClienteRepositoryImpl clienteRepository;

    @Autowired
    public ReservaRepositoryImpl(IReservaRepository iReservaRepository, RestauranteRepositoryImpl iRestauranteRepository, ClienteRepositoryImpl clienteRepository) {
        this.repository = iReservaRepository;
        this.restauranteRepository = iRestauranteRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Reserva resgistar(Reserva reserva) {
        final ReservaTable reservaTable = this.convertReservaDomainToReservaDataBaseEntity(reserva);
        return this.repository.save(reservaTable).ToDomainEntity();
    }

    private ReservaTable convertReservaDomainToReservaDataBaseEntity(Reserva reservaDomain) {
        final RestauranteTable restauranteTable = this.restauranteRepository.findRestauranteTableById(reservaDomain.getRestauranteId());
        final ClienteTable clienteTable = this.clienteRepository.findClienteById(reservaDomain.getCliente().getIdentificador());

        final ReservaTable reservaTable = new ReservaTable();
        reservaTable.setRestaurante(restauranteTable);
        reservaTable.setClienteTable(clienteTable);
        reservaTable.setData(reservaDomain.getData());
        reservaTable.setHora(reservaDomain.getHora());
        reservaTable.setStatus(reservaDomain.getStatus());
        return reservaTable;
    }


}
