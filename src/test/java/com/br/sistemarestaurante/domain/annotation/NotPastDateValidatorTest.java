package com.br.sistemarestaurante.domain.annotation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class NotPastDateValidatorTest {
    @Test
    void isValidTest() {
        NotPastDateValidator validator = new NotPastDateValidator();
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        Calendar dataPresente = Calendar.getInstance();
        assertTrue(validator.isValid(dataPresente, context));
    }
}
