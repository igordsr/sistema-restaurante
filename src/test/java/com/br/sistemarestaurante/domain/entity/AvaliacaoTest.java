package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.SystemException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class AvaliacaoTest {

    @Test
    public void testValidate_ValidAvaliacao_ShouldNotThrowException() {
        UUID identificador = UUID.randomUUID();
        UUID reservaId = UUID.randomUUID();
        String comentario = "Bom serviço!";
        Integer classificacao = 5;

        Avaliacao avaliacao = new Avaliacao(identificador, reservaId, comentario, classificacao);

        assertEquals(avaliacao, avaliacao.validate());
    }

    @Test
    public void testValidate_NullReservaId_ShouldThrowSystemException() {
        UUID identificador = UUID.randomUUID();
        UUID reservaId = null;
        String comentario = "Bom serviço!";
        Integer classificacao = 5;

        Avaliacao avaliacao = new Avaliacao(identificador, reservaId, comentario, classificacao);

        SystemException exception = assertThrows(SystemException.class, avaliacao::validate);
        assertEquals("[reserva Id] não pode ser nulo ou branco", exception.getMessage());
    }
}
