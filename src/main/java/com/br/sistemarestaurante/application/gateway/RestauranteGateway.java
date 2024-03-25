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
import java.util.Optional;

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
        return this.toDTO(restaurante);
    }

    public List<RestauranteDTO> buscar(RestauranteSearchDTO restauranteSearchDTO) {
        String nome = Optional.ofNullable(restauranteSearchDTO.nome()).orElse("");
        String localizacao = Optional.ofNullable(restauranteSearchDTO.localizacao()).orElse("");
        String tipoCozinha = Optional.ofNullable(restauranteSearchDTO.tipoCozinha()).orElse("");

        Restaurante restaurante = new Restaurante(nome, localizacao, tipoCozinha);
        return new RestauranteUseCase(repository).findByNomeOrLocalizacaoOrTipoCozinha(restaurante).stream().map(this::toDTO).toList();

    }

    @Override
    public RestauranteDTO toDTO(Restaurante restaurante) {
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
