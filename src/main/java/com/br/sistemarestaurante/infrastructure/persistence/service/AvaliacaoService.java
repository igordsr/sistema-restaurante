package com.br.sistemarestaurante.infrastructure.persistence.service;

import com.br.sistemarestaurante.domain.entity.Avaliacao;
import com.br.sistemarestaurante.domain.exception.ReservaNotFoundException;
import com.br.sistemarestaurante.infrastructure.persistence.domaincontracts.IAvaliacaoRepositoryDomainContract;
import com.br.sistemarestaurante.infrastructure.persistence.entity.AvaliacaoTable;
import com.br.sistemarestaurante.infrastructure.persistence.entity.ReservaTable;
import com.br.sistemarestaurante.infrastructure.persistence.repository.IAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AvaliacaoService implements IAvaliacaoRepositoryDomainContract {
    private final IAvaliacaoRepository repository;
    private final ReservaService reservaService;

    @Autowired
    public AvaliacaoService(IAvaliacaoRepository iAvaliacaoRepository, ReservaService reservaService) {
        this.repository = iAvaliacaoRepository;
        this.reservaService = reservaService;
    }


    @Override
    public Optional<Avaliacao> findAvaliacaoById(UUID id) {
        return this.repository.findById(id).map(AvaliacaoTable::ToDomainEntity);
    }

    @Override
    public List<Avaliacao> findByReservaTable(UUID id) {
        final ReservaTable reservaTable = this.reservaService.buscarReservaTablePorId(id).orElseThrow(ReservaNotFoundException::new);

        return this.repository.findByReservaTable(reservaTable).stream()
                .map(AvaliacaoTable::ToDomainEntity)
                .toList();
    }

    @Override
    public Avaliacao registrar(Avaliacao avaliacao) {
        final AvaliacaoTable avaliacaoTable = AvaliacaoTable.getInstance(avaliacao);
        ReservaTable reservaTable = this.reservaService.buscarReservaTablePorId(avaliacao.getReservaId()).orElseThrow(ReservaNotFoundException::new);
        avaliacaoTable.setReservaTable(reservaTable);
        return this.repository.save(avaliacaoTable).ToDomainEntity();
    }

}
