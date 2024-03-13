package com.br.sistemarestaurante.adapter.gateway;

import com.br.sistemarestaurante.adapter.dto.RestauranteDTO;
import com.br.sistemarestaurante.adapter.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDTO;
import com.br.sistemarestaurante.adapter.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.usecase.scenario.RestauranteUseCase;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.usecase.RestauranteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteGateway implements IConverterToDTO<RestauranteDTO, Restaurante> {
    private final RestauranteRepositoryImpl repository;

    @Autowired
    public RestauranteGateway(RestauranteRepositoryImpl restauranteRepository) {
        this.repository = restauranteRepository;
    }

    public RestauranteDTO registrar(final IConverterToDomainEntity<Restaurante> obj) {
        Restaurante restaurante = obj.ToDomainEntity();
        restaurante = new RestauranteUseCase().registarNoRepositorioDeDados(this.repository, restaurante);
        return this.ToDTO(restaurante);
    }

    public List<RestauranteTable> buscar(RestauranteSearchDTO restauranteSearchDTO) {
        return new RestauranteUseCase().findByNomeOrLocalizacaoOrTipoCozinha(this.repository, restauranteSearchDTO);

    }

    @Override
    public RestauranteDTO ToDTO(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getIdentificador(),
                restaurante.getNome(),
                restaurante.getLocalizacao(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getCapacidade());
    }
}
