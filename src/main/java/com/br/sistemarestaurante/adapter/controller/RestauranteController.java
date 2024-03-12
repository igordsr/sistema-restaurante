package com.br.sistemarestaurante.adapter.controller;

import com.br.sistemarestaurante.adapter.dto.RestauranteDTO;
import com.br.sistemarestaurante.adapter.gateway.RestauranteGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurante", produces = {"application/json"})
public class RestauranteController {
    private final RestauranteGateway restauranteGateway;


    @Autowired
    public RestauranteController(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> registrar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        restauranteDTO = this.restauranteGateway.registrar(restauranteDTO);
        return new ResponseEntity<>(restauranteDTO, HttpStatus.CREATED);
    }
}
