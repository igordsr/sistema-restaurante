package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.SystemException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteTest {
    @Test
    public void testValidate_ValidRestaurante_ShouldNotThrowException() {
        // Arrange
        UUID identificador = UUID.randomUUID();
        String nome = "Restaurante X";
        String localizacao = "Rua ABC, 123";
        String tipoCozinha = "Italiana";
        LocalTime horarioAbertura = LocalTime.of(9, 0);
        LocalTime horarioFechamento = LocalTime.of(22, 0);
        Integer capacidade = 50;

        Restaurante restaurante = new Restaurante(identificador, nome, localizacao, tipoCozinha, horarioAbertura, horarioFechamento, capacidade);

        assertEquals(restaurante, restaurante.validate());
    }

    @Test
    public void testValidate_NullNome_ShouldThrowSystemException() {
        // Arrange
        UUID identificador = UUID.randomUUID();
        String nome = null;
        String localizacao = "Rua ABC, 123";
        String tipoCozinha = "Italiana";
        LocalTime horarioAbertura = LocalTime.of(9, 0);
        LocalTime horarioFechamento = LocalTime.of(22, 0);
        Integer capacidade = 50;

        Restaurante restaurante = new Restaurante(identificador, nome, localizacao, tipoCozinha, horarioAbertura, horarioFechamento, capacidade);

        SystemException exception = assertThrows(SystemException.class, restaurante::validate);
        assertEquals("[nome] não pode ser nulo ou branco", exception.getMessage());
    }

}
