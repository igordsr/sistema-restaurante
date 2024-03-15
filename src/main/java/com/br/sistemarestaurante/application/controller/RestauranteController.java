package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.application.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.application.gateway.RestauranteGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/restaurantes")
    public List<RestauranteDTO> buscar(
            @ModelAttribute RestauranteSearchDTO restauranteSearchDTO) {
        return this.restauranteGateway.buscar(restauranteSearchDTO);
    }
}
