package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.application.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.application.gateway.AvaliacaoGateway;
import com.br.sistemarestaurante.application.gateway.RestauranteGateway;
import com.br.sistemarestaurante.domain.entity.Avaliacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/avaliacao", produces = {"application/json"})
public class AvaliacaoController {
    private final AvaliacaoGateway avaliacaoGateway;


    @Autowired
    public AvaliacaoController(AvaliacaoGateway avaliacaoGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> registrar(@RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {
        avaliacaoDTO = this.avaliacaoGateway.registrar(avaliacaoDTO);
        return new ResponseEntity<>(avaliacaoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> buscarPorReserva(
            @PathVariable("reservaId")UUID reservaId){
        return new ResponseEntity<>(this.avaliacaoGateway.buscar(reservaId), HttpStatus.NO_CONTENT);
    }

}
