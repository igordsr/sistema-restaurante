package com.br.sistemarestaurante.application.controller.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationMessageTest {
    @Test
    void testConstructorAndGetters() {
        String campo = "nome";
        String mensagem = "O campo nome é obrigatório.";

        ValidationMessage validationMessage = new ValidationMessage(campo, mensagem);

        assertEquals(campo, validationMessage.getCampo());
        assertEquals(mensagem, validationMessage.getMensagem());
    }

    @Test
    void testSetters() {
        ValidationMessage validationMessage = new ValidationMessage();

        String campo = "email";
        String mensagem = "O formato do email é inválido.";
        validationMessage.setCampo(campo);
        validationMessage.setMensagem(mensagem);

        assertEquals(campo, validationMessage.getCampo());
        assertEquals(mensagem, validationMessage.getMensagem());
    }
}
