package com.pablo.calendar_backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<ApiRes<Void>> handleConstraintException(Exception e) {
        ApiRes<Void> response = new ApiRes<>(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiRes<Void>> handleArgumentException(Exception e) {
        ApiRes<Void> response = new ApiRes<>(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ApiRes<Void>> handleRuntimeException(Exception e) {
        ApiRes<Void> response = new ApiRes<>(
                HttpStatus.BAD_GATEWAY.value(),
                e.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

}
