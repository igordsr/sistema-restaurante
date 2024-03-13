package com.br.sistemarestaurante.adapter.controller;

import com.br.sistemarestaurante.adapter.dto.ReservaDTO;
import com.br.sistemarestaurante.adapter.gateway.ReservaGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reserva", produces = {"application/json"})
public class ReservaController {
    private final ReservaGateway reservaGateway;

    @Autowired
    public ReservaController(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> registrar(@RequestBody @Valid ReservaDTO reservaDTO) {
        reservaDTO = this.reservaGateway.registrar(reservaDTO);
        return new ResponseEntity<>(reservaDTO, HttpStatus.CREATED);
    }
}
