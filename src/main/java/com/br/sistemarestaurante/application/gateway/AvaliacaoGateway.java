package com.br.sistemarestaurante.application.gateway;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.application.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.application.util.IConverterToDTO;
import com.br.sistemarestaurante.application.util.IConverterToDomainEntity;
import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.entity.Restaurante;
import com.br.sistemarestaurante.domain.usecase.AvaliacaoUseCase;
import com.br.sistemarestaurante.domain.usecase.RestauranteUseCase;
import com.br.sistemarestaurante.infrastructure.persistence.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvaliacaoGateway implements IConverterToDTO<AvaliacaoDTO, Avaliacao> {
    private final AvaliacaoService repository;

    @Autowired
    public AvaliacaoGateway(AvaliacaoService avaliacaoRepository) {
        this.repository = avaliacaoRepository;
    }

    public AvaliacaoDTO registrar(final IConverterToDomainEntity<Avaliacao> obj) {
        Avaliacao avaliacao = obj.ToDomainEntity();
        avaliacao = new AvaliacaoUseCase(repository).registarNoRepositorioDeDados(avaliacao);
        return this.ToDTO(avaliacao);
    }

    public List<RestauranteDTO> buscar(RestauranteSearchDTO restauranteSearchDTO) {
        Restaurante restaurante = new Restaurante(restauranteSearchDTO.nome(), restauranteSearchDTO.localizacao(), restauranteSearchDTO.tipoCozinha());
        return new RestauranteUseCase(repository).findByNomeOrLocalizacaoOrTipoCozinha(restaurante).stream().map(this::ToDTO).collect(Collectors.toList());

    }

    @Override
    public AvaliacaoDTO ToDTO(Avaliacao avaliacao) {
        return new AvaliacaoDTO(
                avaliacao.getIdentificador(),
                avaliacao.getReservaId(),
                avaliacao.getComentario(),
                avaliacao.getClassificacao()
        );
    }
}
