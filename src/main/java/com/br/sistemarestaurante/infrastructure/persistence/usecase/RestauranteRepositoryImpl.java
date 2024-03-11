package com.br.sistemarestaurante.infrastructure.persistence.usecase;

import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.servicecontracts.IRegistrarRestaurante;
import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteRepositoryImpl implements IRegistrarRestaurante {
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
