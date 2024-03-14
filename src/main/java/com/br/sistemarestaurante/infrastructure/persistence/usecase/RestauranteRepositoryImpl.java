package com.br.sistemarestaurante.infrastructure.persistence.usecase;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.exception.RestauranteNotFoundException;
import com.br.sistemarestaurante.infrastructure.persistence.domaincontracts.IRestauranteRepositoryDomainContract;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestauranteRepositoryImpl implements IRestauranteRepositoryDomainContract {
    private IRestauranteRepository repository;

    @Autowired
    public RestauranteRepositoryImpl(IRestauranteRepository iRestauranteRepository) {
        this.repository = iRestauranteRepository;
    }

    @Override
    public Restaurante resgistar(Restaurante restaurante) {
        final RestauranteTable restauranteTable = this.convertRestauranteDomainToRestauranteDataBaseEntity(restaurante);
        return this.repository.save(restauranteTable).ToDomainEntity();
    }

    @Override
    public Optional<Restaurante> findRestauranteById(UUID id) {
        return this.repository.findById(id).map(RestauranteTable::ToDomainEntity);
    }

    @Override
    public List<Restaurante> findByNomeOrLocalizacaoOrTipoCozinha(Restaurante restaurante) {
        final List<RestauranteTable> restaurantes = this.repository.findByNomeContainingAndLocalizacaoContainingAndTipoCozinhaContaining(restaurante.getNome(), restaurante.getLocalizacao(), restaurante.getTipoCozinha());
        return restaurantes.stream().map(RestauranteTable::ToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public RestauranteTable findRestauranteTableById(UUID id) throws RestauranteNotFoundException {
        return this.repository.findById(id).orElseThrow(RestauranteNotFoundException::new);
    }

    private RestauranteTable convertRestauranteDomainToRestauranteDataBaseEntity(Restaurante restauranteDomain) {
        final RestauranteTable restauranteTable = new RestauranteTable();
        restauranteTable.setNome(restauranteDomain.getNome());
        restauranteTable.setLocalizacao(restauranteDomain.getLocalizacao());
        restauranteTable.setTipoCozinha(restauranteDomain.getTipoCozinha());
        restauranteTable.setHorarioAbertura(restauranteDomain.getHorarioAbertura());
        restauranteTable.setHorarioFechamento(restauranteDomain.getHorarioFechamento());
        restauranteTable.setCapacidade(restauranteDomain.getCapacidade());
        return restauranteTable;
    }


}
