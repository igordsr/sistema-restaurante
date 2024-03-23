package com.br.sistemarestaurante.application.controller.exception;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class ValidateErrorTest {

    @Test
    void testAddAndGetMessages() {
        ValidateError error = new ValidateError();

        error.addMensagens("campo1", "mensagem1");
        error.addMensagens("campo2", "mensagem2");

        List<ValidationMessage> messages = error.getMessages();

        assertEquals(2, messages.size());
        assertEquals("campo1", messages.get(0).getCampo());
        assertEquals("mensagem1", messages.get(0).getMensagem());
        assertEquals("campo2", messages.get(1).getCampo());
        assertEquals("mensagem2", messages.get(1).getMensagem());
    }
}
