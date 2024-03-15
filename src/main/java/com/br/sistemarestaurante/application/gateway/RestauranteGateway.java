package com.br.sistemarestaurante.application.gateway;

import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.application.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.application.util.IConverterToDTO;
import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.usecase.RestauranteUseCase;
import com.br.sistemarestaurante.infrastructure.persistence.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteGateway implements IConverterToDTO<RestauranteDTO, Restaurante> {
    private final RestauranteService repository;

    @Autowired
    public RestauranteGateway(RestauranteService restauranteRepository) {
        this.repository = restauranteRepository;
    }

    public RestauranteDTO registrar(final IConverterToDomainEntity<Restaurante> obj) {
        Restaurante restaurante = obj.ToDomainEntity();
        restaurante = new RestauranteUseCase(repository).registarNoRepositorioDeDados(restaurante);
        return this.ToDTO(restaurante);
    }

    public List<RestauranteDTO> buscar(RestauranteSearchDTO restauranteSearchDTO) {
        Restaurante restaurante = new Restaurante(restauranteSearchDTO.nome(), restauranteSearchDTO.localizacao(), restauranteSearchDTO.tipoCozinha());
        return new RestauranteUseCase(repository).findByNomeOrLocalizacaoOrTipoCozinha(restaurante).stream().map(this::ToDTO).collect(Collectors.toList());

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
