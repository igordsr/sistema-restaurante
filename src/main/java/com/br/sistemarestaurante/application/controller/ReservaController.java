package com.br.sistemarestaurante.application.controller;

import com.br.sistemarestaurante.application.controller.exception.StardardError;
import com.br.sistemarestaurante.application.dto.ReservaDTO;
import com.br.sistemarestaurante.application.gateway.ReservaGateway;
import com.br.sistemarestaurante.domain.entity.StatusReserva;
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
@Tag(name = "Reserva")
@RequestMapping(value = "/reserva", produces = {"application/json"})
public class ReservaController {
    private final ReservaGateway reservaGateway;

    @Autowired
    public ReservaController(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    @PostMapping
    @Operation(summary = "Resgistro de Reserva", description = "Esté metodo tem como finalidade permitir que o dono de um Restaurante cadastre uma Reserva", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do Reserva realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo Reserva", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<ReservaDTO> registrar(@RequestBody @Valid ReservaDTO reservaDTO) {
        reservaDTO = this.reservaGateway.registrar(reservaDTO);
        return new ResponseEntity<>(reservaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Atualização de Reserva", description = "Esté metodo tem como finalidade permitir que o dono de um Restaurante altere uma Reserva", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do Reserva realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo Reserva", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ReservaDTO alterarStatus(@PathVariable("id") UUID id, @RequestBody @Valid StatusReserva status) {
        return this.reservaGateway.alterarStatus(id, status);
    }

    @GetMapping("/restaurante/{id}")
    @Operation(summary = "Lista de Reserva do Restaurante", description = "Esté metodo tem como finalidade permitir que o dono de um Restaurante liste todas as Reservas do estabelecimento", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do Restaurante realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo Restaurante", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public List<ReservaDTO> buscar(
            @PathVariable("id") UUID id) {
        return this.reservaGateway.buscarReservaPorRestaurante(id);
    }
}
