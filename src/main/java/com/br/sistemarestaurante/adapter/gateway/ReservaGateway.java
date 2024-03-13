package com.br.sistemarestaurante.adapter.gateway;

import com.br.sistemarestaurante.adapter.dto.ReservaDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Reserva;
import com.br.sistemarestaurante.domain.usecase.scenario.ReservaUseCaseImpl;
import com.br.sistemarestaurante.infrastructure.persistence.usecase.ReservaRepositoryImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaGateway implements IConverterToDTO<ReservaDTO, Reserva> {
    private final ReservaRepositoryImpl repository;

    @Autowired
    public ReservaGateway(ReservaRepositoryImpl reservaRepository) {
        this.repository = reservaRepository;
    }

    public ReservaDTO registrar(final IConverterToDomainEntity<Reserva> obj) {
        Reserva reserva = obj.ToDomainEntity();
        reserva = new ReservaUseCaseImpl().registarNoRepositorioDeDados(this.repository, reserva);
        return this.ToDTO(reserva);
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
