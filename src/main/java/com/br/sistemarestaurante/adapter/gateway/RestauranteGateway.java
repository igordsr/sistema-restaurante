package com.br.sistemarestaurante.adapter.gateway;

import com.br.sistemarestaurante.adapter.dto.RestauranteDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.usecase.scenario.RestauranteUseCaseImpl;
import com.br.sistemarestaurante.infrastructure.persistence.usecase.RestauranteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteGateway implements IConverterToDTO<RestauranteDTO, Restaurante> {
    private final RestauranteRepositoryImpl repository;

    @Autowired
    public RestauranteGateway(RestauranteRepositoryImpl restauranteRepository) {
        this.repository = restauranteRepository;
    }

    public RestauranteDTO registrar(final IConverterToDomainEntity<Restaurante> obj) {
        Restaurante restaurante = obj.ToDomainEntity();
        restaurante = new RestauranteUseCaseImpl().registarNoRepositorioDeDados(this.repository, restaurante);
        return this.ToDTO(restaurante);
    }

    @Override
    public RestauranteDTO ToDTO(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getIdentificador(),
                restaurante.getNome(),
                restaurante.getLocalizacao(),
                restaurante.getTipoCozinha(),
                restaurante.getHorariosFuncionamento(),
                restaurante.getCapacidade());
    }
}
