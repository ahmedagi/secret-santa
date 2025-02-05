package org.example.secretsanta.controller.advice;

import org.example.secretsanta.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .status(500)
                .body(new ErrorDetails(e.getMessage()));
    }
}
