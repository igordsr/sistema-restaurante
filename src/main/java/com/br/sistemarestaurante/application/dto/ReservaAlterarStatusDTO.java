package com.br.sistemarestaurante.application.dto;

import com.br.sistemarestaurante.domain.entity.StatusReserva;

import java.util.UUID;

public record ReservaAlterarStatusDTO(UUID reservaId, StatusReserva statusReserva) {
}
