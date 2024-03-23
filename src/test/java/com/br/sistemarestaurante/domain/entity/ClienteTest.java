package com.br.sistemarestaurante.domain.entity;

import com.br.sistemarestaurante.domain.exception.SystemException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTest {
    @Test
    public void testValidate_ValidCliente_ShouldNotThrowException() {
        UUID identificador = UUID.randomUUID();
        String nome = "Fulano";
        String email = "fulano@example.com";
        String telefone = "123456789";

        Cliente cliente = new Cliente(identificador, nome, email, telefone);

        assertEquals(cliente, cliente.validate());
    }

    @Test
    public void testValidate_NullNome_ShouldThrowSystemException() {
        UUID identificador = UUID.randomUUID();
        String nome = null;
        String email = "fulano@example.com";
        String telefone = "123456789";

        Cliente cliente = new Cliente(identificador, nome, email, telefone);

        SystemException exception = assertThrows(SystemException.class, cliente::validate);
        assertEquals("[nome] não pode ser nulo ou branco", exception.getMessage());
    }
}
