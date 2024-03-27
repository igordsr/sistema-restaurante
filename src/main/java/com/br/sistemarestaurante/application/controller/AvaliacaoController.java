package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.controller.exception.StardardError;
import com.br.sistemarestaurante.application.dto.AvaliacaoDTO;
import com.br.sistemarestaurante.application.gateway.AvaliacaoGateway;
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
import java.util.UUID;

@RestController
@Tag(name = "Avaliação")
@RequestMapping(value = "/avaliacao", produces = {"application/json"})
public class AvaliacaoController {
    private final AvaliacaoGateway avaliacaoGateway;


    @Autowired
    public AvaliacaoController(AvaliacaoGateway avaliacaoGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
    }

    @PostMapping
    @Operation(summary = "Resgistro de Avaliação", description = "Esté metodo tem como finalidade permitir que o usuário de um Restaurante cadastre uma Avaliação", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro da Avaliação realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo Avaliação", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<AvaliacaoDTO> registrar(@RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {
        avaliacaoDTO = this.avaliacaoGateway.registrar(avaliacaoDTO);
        return new ResponseEntity<>(avaliacaoDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @Operation(summary = "Lista de Avaliação", description = "Esté metodo tem como finalidade permitir listar as Avaliações do Restaurante", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro da Avaliação realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo Avaliação", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public List<AvaliacaoDTO> buscarPorAvaliacao(@PathVariable("id")UUID reservaId){
        return this.avaliacaoGateway.buscar(reservaId);
    }

}
