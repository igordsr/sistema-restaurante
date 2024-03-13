package com.br.sistemarestaurante.infrastructure.persistence.usecase;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.exception.ReservaException;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarReserva;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IReservaRepository;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaRepositoryImpl implements IRegistrarReserva {
    private IReservaRepository repository;
    private IRestauranteRepository restauranteRepository;
    private ClienteRepositoryImpl clienteRepository;

    @Autowired
    public ReservaRepositoryImpl(IReservaRepository iReservaRepository, IRestauranteRepository iRestauranteRepository, ClienteRepositoryImpl clienteRepository) {
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
        RestauranteTable restauranteTable = this.restauranteRepository.findById(reservaDomain.getRestauranteId()).orElseThrow(() -> new ReservaException("Restaurante não encontrado!"));
        ClienteTable clienteTable = this.clienteRepository.findCliente(reservaDomain.getCliente());

        final ReservaTable reservaTable = new ReservaTable();
        reservaTable.setRestaurante(restauranteTable);
        reservaTable.setClienteTable(clienteTable);
        reservaTable.setData(reservaDomain.getData());
        reservaTable.setHora(reservaDomain.getHora());
        reservaTable.setStatus(reservaDomain.getStatus());
        return reservaTable;
    }


}
