package com.br.sistemarestaurante.adapter.gateway;

import com.br.sistemarestaurante.adapter.dto.ReservaDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.usecase.scenario.ReservaUseCase;
import com.br.sistemarestaurante.infrastructure.persistence.usecase.ClienteRepositoryImpl;
import com.br.sistemarestaurante.infrastructure.persistence.usecase.ReservaRepositoryImpl;
import com.br.sistemarestaurante.infrastructure.persistence.usecase.RestauranteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReservaGateway implements IConverterToDTO<ReservaDTO, Reserva> {
    private final ReservaRepositoryImpl repository;
    private final ClienteRepositoryImpl clienteRepository;
    private final RestauranteRepositoryImpl restauranteRepository;

    @Autowired
    public ReservaGateway(ReservaRepositoryImpl reservaRepository, ClienteRepositoryImpl clienteRepository, RestauranteRepositoryImpl restauranteRepository) {
        this.repository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public ReservaDTO registrar(final IConverterToDomainEntity<Reserva> obj) {
        Reserva reserva = obj.ToDomainEntity();
        reserva = new ReservaUseCase().registarNoRepositorioDeDados(this.repository, restauranteRepository, this.clienteRepository, reserva);
        return this.ToDTO(reserva);
    }

    public ReservaDTO alterarStatus(UUID id, StatusReserva status) {
        Reserva reserva = new Reserva(id, status);
        return this.ToDTO(new ReservaUseCase().reservaAlterarStatus(this.repository, reserva));
    }


    @Override
    public ReservaDTO ToDTO(Reserva reserva) {

        return new ReservaDTO(
                reserva.getIdentificador(),
                reserva.getRestauranteId(),
                reserva.getCliente().getNome(),
                reserva.getCliente().getEmail(),
                reserva.getCliente().getTelefone(),
                reserva.getData(),
                reserva.getHora(),
                reserva.getStatus()
        );
    }
}
