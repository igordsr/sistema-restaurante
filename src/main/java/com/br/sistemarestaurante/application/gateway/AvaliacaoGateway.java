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
import com.br.sistemarestaurante.infrastructure.persistence.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvaliacaoGateway implements IConverterToDTO<AvaliacaoDTO, Avaliacao> {
    private final AvaliacaoService repository;
    private final RestauranteService restauranteService;

    @Autowired
    public AvaliacaoGateway(AvaliacaoService avaliacaoRepository, RestauranteService restauranteService) {
        this.repository = avaliacaoRepository;
        this.restauranteService = restauranteService;
    }

    public AvaliacaoDTO registrar(final IConverterToDomainEntity<Avaliacao> obj) {
        Avaliacao avaliacao = obj.ToDomainEntity();
        avaliacao = new AvaliacaoUseCase(repository).registarNoRepositorioDeDados(avaliacao);
        return this.toDTO(avaliacao);
    }

    public List<RestauranteDTO> buscar(RestauranteSearchDTO restauranteSearchDTO) {
        Restaurante restaurante = new Restaurante(
                restauranteSearchDTO.nome(),
                restauranteSearchDTO.localizacao(),
                restauranteSearchDTO.tipoCozinha()
        );

        return new RestauranteUseCase(restauranteService)
                .findByNomeOrLocalizacaoOrTipoCozinha(restaurante)
                .stream()
                .map(this::converterRestauranteParaDTO)
                .toList();
    }

    private RestauranteDTO converterRestauranteParaDTO(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getIdentificador(),
                restaurante.getNome(),
                restaurante.getLocalizacao(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getCapacidade()
        );
    }
    @Override
    public AvaliacaoDTO toDTO(Avaliacao avaliacao) {
        return new AvaliacaoDTO(
                avaliacao.getIdentificador(),
                avaliacao.getReservaId(),
                avaliacao.getComentario(),
                avaliacao.getClassificacao()
        );
    }
}
