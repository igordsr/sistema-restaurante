package com.br.sistemarestaurante.infrastructure.persistence.repository;

import com.br.sistemarestaurante.infrastructure.persistence.entity.RestauranteTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IRestauranteRepository extends JpaRepository<RestauranteTable, UUID> {

    List<RestauranteTable> findByNomeContainingAndLocalizacaoContainingAndTipoCozinhaContaining(@Param("nome") String nome, @Param("localizacao") String localizacao, @Param("tipoCozinha") String tipoCozinha);
}
