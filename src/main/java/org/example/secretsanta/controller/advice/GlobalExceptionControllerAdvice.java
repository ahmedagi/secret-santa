package org.example.secretsanta.controller.advice;

import org.apache.coyote.BadRequestException;
import org.example.secretsanta.exception.EmployeeNotFoundException;
import org.example.secretsanta.model.ErrorDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof SQLException sqlException) {
            String message = sqlException.getMessage();

            if (message.contains("employee_name_key")) {
                return ResponseEntity
                        .status(400)
                        .body(new ErrorDetails("Name must be unique. The provided name already exists."));
            }
        }

        return ResponseEntity.status(500).body(new ErrorDetails("Internal server error."));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(Exception e) {
        return ResponseEntity
                .status(400)
                .body(new ErrorDetails(e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(Exception e) {
        return ResponseEntity
                .status(400)
                .body(new ErrorDetails(e.getMessage()));
    }
}
