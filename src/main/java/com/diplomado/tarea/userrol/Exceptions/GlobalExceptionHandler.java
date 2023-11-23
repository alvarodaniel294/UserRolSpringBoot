package com.diplomado.tarea.userrol.Exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException roleNotFoundException) {
        return new ResponseEntity<>("Role not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintsViolationException(ConstraintViolationException customConstraintsViolationException) {
        StringBuilder message = new StringBuilder();
        message.append("Validation Error:");
        message.append("[");
        customConstraintsViolationException.getConstraintViolations().forEach(violation -> {
            message.append(violation.getPropertyPath()).append(" ").append(violation.getMessage());
            message.append(",");
        });
        message.append("]");
        return new ResponseEntity<>(message.toString(), HttpStatus.PRECONDITION_FAILED);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
