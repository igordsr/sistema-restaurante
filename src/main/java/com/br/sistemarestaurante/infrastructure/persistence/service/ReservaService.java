package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.exception.ReservaNotFoundException;
import com.br.sistemarestaurante.infrastructure.persistence.domaincontracts.IReservaRepositoryDomainContract;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ClienteTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

@Component
public class ReservaService implements IReservaRepositoryDomainContract {
    private final IReservaRepository repository;
    private final RestauranteService restauranteService;
    private final ClienteService clienteService;

    @Autowired
    public ReservaService(IReservaRepository iReservaRepository, RestauranteService iRestauranteRepository, ClienteService clienteService) {
        this.repository = iReservaRepository;
        this.restauranteService = iRestauranteRepository;
        this.clienteService = clienteService;
    }

    @Override
    public Reserva resgistar(Reserva reserva) {
        final RestauranteTable restauranteTable = this.restauranteService.findRestauranteTableById(reserva.getRestauranteId());
        final ClienteTable clienteTable = this.clienteService.findClienteById(reserva.getCliente().getIdentificador());

        final ReservaTable reservaTable = ReservaTable.getInstance(reserva, restauranteTable, clienteTable);
        return this.repository.save(reservaTable).ToDomainEntity();
    }

    @Override
    public List<Reserva> listarReservasRestaurantePeloStatusReserva(Restaurante restaurante, Calendar data, LocalTime hora, StatusReserva statusReserva) {
        final RestauranteTable instance = RestauranteTable.getInstance(restaurante);
        return this.repository.findByRestauranteAndDataAndHoraAndStatus(instance, data, hora, statusReserva)
                .stream()
                .map(ReservaTable::ToDomainEntity)
                .toList();
    }

    @Override
    public Reserva aterarStatusDaReserva(Reserva reserva) {
        ReservaTable reservaTable = this.repository.findById(reserva.getIdentificador()).orElseThrow(ReservaNotFoundException::new);
        reservaTable.setStatus(reserva.getStatus());
        return this.repository.save(reservaTable).ToDomainEntity();
    }
}
