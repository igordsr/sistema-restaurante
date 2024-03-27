package com.br.sistemarestaurante.application.controller.exception;

import com.br.sistemarestaurante.domain.exception.SystemException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
class ControllerExceptionHandler {
    private StardardError stardardError = new StardardError();


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StardardError> validation(MethodArgumentNotValidException err, HttpServletRequest httpServletRequest) {
        HttpStatus code = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(code.value());
        validateError.setError("Erro de Validação");
        validateError.setMessage(err.getMessage());
        validateError.setPath(httpServletRequest.getRequestURI());
        for (FieldError f : err.getBindingResult().getFieldErrors()) {
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(code).body(validateError);
    }

    @ExceptionHandler(SystemException.class)
    ResponseEntity<StardardError> InvalidBusinessRules(SystemException err, HttpServletRequest httpServletRequest) {
        HttpStatus code = HttpStatus.BAD_REQUEST;
        stardardError.setTimestamp(Instant.now());
        stardardError.setStatus(code.value());
        stardardError.setError(err.getMessage());
        stardardError.setMessage(err.getMessage());
        stardardError.setPath(httpServletRequest.getRequestURI());
        return ResponseEntity.status(code).body(this.stardardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<StardardError> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorMessage = "Ocorreu um erro ao processar a solicitação.";

        StardardError standardError = new StardardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(HttpStatus.CONFLICT.value());
        standardError.setError("Já existe um registro com os mesmos dados. Por favor, verifique os dados e tente novamente.");
        standardError.setMessage(errorMessage);
        standardError.setPath(request.getDescription(false));

        return ResponseEntity.status(status).body(standardError);
    }

}