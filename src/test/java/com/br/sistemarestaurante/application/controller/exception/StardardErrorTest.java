package com.br.sistemarestaurante.application.controller.exception;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StardardErrorTest {

    @Test
    void testGettersAndSetters() {
        StardardError error = new StardardError();

        Instant timestamp = Instant.now();
        Integer status = 404;
        String errorMessage = "Not Found";
        String message = "test not found";
        String path = "/api/test";

        error.setTimestamp(timestamp);
        error.setStatus(status);
        error.setError(errorMessage);
        error.setMessage(message);
        error.setPath(path);

        assertEquals(timestamp, error.getTimestamp());
        assertEquals(status, error.getStatus());
        assertEquals(errorMessage, error.getError());
        assertEquals(message, error.getMessage());
        assertEquals(path, error.getPath());
    }
}
