package com.br.sistemarestaurante.domain.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotPastDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotPastDate {
    String message() default "Data do Agendamento Inválido! A Data do Agendamento não pode ser anterior à data atual.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
