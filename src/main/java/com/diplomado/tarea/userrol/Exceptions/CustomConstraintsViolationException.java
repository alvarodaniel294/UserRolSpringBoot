package com.diplomado.tarea.userrol.Exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class CustomConstraintsViolationException extends ConstraintViolationException {
    public CustomConstraintsViolationException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();
        message.append("Validation Error:");
        message.append("[");
        this.getConstraintViolations().forEach(violation -> {
            message.append(violation.getPropertyPath()).append(" ").append(violation.getMessage());
            message.append(",");
        });
        message.append("]");
        return message.toString();
    }
}
