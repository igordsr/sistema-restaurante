package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.ReservaDTO;
import com.br.sistemarestaurante.application.gateway.ReservaGateway;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PutMapping("/{id}/status")
    public ReservaDTO alterarStatus(@PathVariable("id") UUID id, @RequestBody @Valid StatusReserva status) {
        return this.reservaGateway.alterarStatus(id, status);
    }

    @GetMapping("/restaurante/{id}")
    public List<ReservaDTO> buscar(
            @PathVariable("id") UUID id) {
        return this.reservaGateway.buscarReservaPorRestaurante(id);
    }
}
