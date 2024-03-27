package com.br.sistemarestaurante.application.controller.exception;

import com.br.sistemarestaurante.domain.exception.SystemException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerExceptionHandlerTest {
    @Test
    void testValidation() {
        ControllerExceptionHandler handler = new ControllerExceptionHandler();
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(exception.getMessage()).thenReturn("Erro de validação");
        when(request.getRequestURI()).thenReturn("/api/resource");
        when(exception.getBindingResult()).thenReturn(bindingResult);

        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("objectName", "fieldName", "errorMessage"));
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        ResponseEntity<StardardError> response = handler.validation(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro de Validação", response.getBody().getError());
        assertEquals("/api/resource", response.getBody().getPath());
    }

    @Test
    void testInvalidBusinessRules() {
        ControllerExceptionHandler handler = new ControllerExceptionHandler();
        SystemException exception = new SystemException("Erro de regra de negócio");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI()).thenReturn("/api/resource");

        ResponseEntity<StardardError> response = handler.InvalidBusinessRules(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro de regra de negócio", response.getBody().getError());
        assertEquals("/api/resource", response.getBody().getPath());
    }

    @Test
    void testDataIntegrityViolationException() {
        ControllerExceptionHandler handler = new ControllerExceptionHandler();
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Mensagem de erro");
        WebRequest request = mock(WebRequest.class);

        when(request.getDescription(false)).thenReturn("/api/resource");

        ResponseEntity<StardardError> response = handler.handleDataIntegrityViolationException(exception, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Já existe um registro com os mesmos dados. Por favor, verifique os dados e tente novamente.", response.getBody().getError());
        assertEquals("/api/resource", response.getBody().getPath());
    }
}
