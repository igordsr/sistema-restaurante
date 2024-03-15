package com.br.sistemarestaurante.infrastructure.persistence.usecase;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.exception.ReservaNotFoundException;
import com.br.sistemarestaurante.infrastructure.persistence.domaincontracts.IRepositoryDomainContractReserva;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositoryImplReserva implements IRepositoryDomainContractReserva {
    private final IReservaRepository repository;
    private final RestauranteRepositoryImpl restauranteRepository;
    private final ClienteRepositoryImpl clienteRepository;

    @Autowired
    public RepositoryImplReserva(IReservaRepository iReservaRepository, RestauranteRepositoryImpl iRestauranteRepository, ClienteRepositoryImpl clienteRepository) {
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


    @Override
    public List<Reserva> listarReservasRestaurantePeloStatusReserva(Restaurante restaurante, Calendar data, LocalTime hora, StatusReserva statusReserva) {
        final RestauranteTable instance = RestauranteTable.getInstance(restaurante);
        return this.repository.findByRestauranteAndDataAndHoraAndStatus(instance, data, hora, statusReserva)
                .stream()
                .map(ReservaTable::ToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Reserva aterarStatusDaReserva(Reserva reserva) {
        ReservaTable reservaTable = this.repository.findById(reserva.getIdentificador()).orElseThrow(ReservaNotFoundException::new);
        reservaTable.setStatus(reserva.getStatus());
        return this.repository.save(reservaTable).ToDomainEntity();
    }
}
