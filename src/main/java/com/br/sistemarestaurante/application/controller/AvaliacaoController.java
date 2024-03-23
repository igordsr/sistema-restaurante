package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import com.br.sistemarestaurante.application.gateway.AvaliacaoGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
