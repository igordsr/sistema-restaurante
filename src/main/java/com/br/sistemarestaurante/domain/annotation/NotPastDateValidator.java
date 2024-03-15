package com.br.sistemarestaurante.domain.annotation;

import com.br.sistemarestaurante.domain.usecase.rule.IVerificarDataDaReservaRule;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Calendar;

public class NotPastDateValidator implements ConstraintValidator<NotPastDate, Calendar> {
    @Override
    public boolean isValid(Calendar data, ConstraintValidatorContext context) {
        return IVerificarDataDaReservaRule.isValid(data);
    }


}