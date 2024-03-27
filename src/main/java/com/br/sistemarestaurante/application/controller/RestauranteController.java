package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.controller.exception.StardardError;
import com.br.sistemarestaurante.application.dto.RestauranteDTO;
import com.br.sistemarestaurante.application.dto.RestauranteSearchDTO;
import com.br.sistemarestaurante.application.gateway.RestauranteGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Restaurante")
@RequestMapping(value = "/restaurante", produces = {"application/json"})
public class RestauranteController {
    private final RestauranteGateway restauranteGateway;

    @Autowired
    public RestauranteController(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @PostMapping
    @Operation(summary = "Resgistro de Restaurante", description = "Esté metodo tem como finalidade permitir que o dono de um Restaurante cadastre se estabelecimento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do Restaurante realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo Restaurante", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<RestauranteDTO> registrar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        restauranteDTO = this.restauranteGateway.registrar(restauranteDTO);
        return new ResponseEntity<>(restauranteDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Consulta de Restaurantes", description = "Esté metodo tem como finalidade permitir que os usuários consultem a lista de Restaurante", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta do Restaurante realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo Restaurante", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public List<RestauranteDTO> buscar(
            @ModelAttribute RestauranteSearchDTO restauranteSearchDTO) {
        return this.restauranteGateway.buscar(restauranteSearchDTO);
    }
}
