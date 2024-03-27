package com.br.sistemarestaurante.application.gateway;

import com.br.sistemarestaurante.application.dto.ReservaDTO;
import com.br.sistemarestaurante.application.util.IConverterToDTO;
import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import com.br.sistemarestaurante.domain.usecase.ReservaUseCase;
import com.br.sistemarestaurante.infrastructure.persistence.service.ClienteService;
import com.br.sistemarestaurante.infrastructure.persistence.service.ReservaService;
import com.br.sistemarestaurante.infrastructure.persistence.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ReservaGateway implements IConverterToDTO<ReservaDTO, Reserva> {
    private final ReservaService reservaService;
    private final ClienteService clienteService;
    private final RestauranteService restauranteService;

    @Autowired
    public ReservaGateway(ReservaService reservaRepository, ClienteService clienteService, RestauranteService restauranteService) {
        this.reservaService = reservaRepository;
        this.clienteService = clienteService;
        this.restauranteService = restauranteService;
    }

    public ReservaDTO registrar(final IConverterToDomainEntity<Reserva> obj) {
        Reserva reserva = obj.ToDomainEntity();
        reserva = new ReservaUseCase(this.reservaService, restauranteService, this.clienteService).registarNoRepositorioDeDados(reserva);
        return this.toDTO(reserva);
    }

    public ReservaDTO alterarStatus(UUID id, StatusReserva status) {
        Reserva reserva = new Reserva(id, status);
        return this.toDTO(new ReservaUseCase(this.reservaService, restauranteService, this.clienteService).alterarStatusDaReserva(reserva));
    }

    public List<ReservaDTO> buscarReservaPorRestaurante(UUID restauranteId) {
        return new ReservaUseCase(this.reservaService, restauranteService, this.clienteService).buscarReservaPorRestaurante(this.reservaService, restauranteId).stream().map(this::toDTO).toList();
    }


    @Override
    public ReservaDTO toDTO(Reserva reserva) {

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
