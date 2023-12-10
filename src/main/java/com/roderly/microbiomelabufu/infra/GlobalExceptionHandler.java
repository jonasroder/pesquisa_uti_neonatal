package com.roderly.microbiomelabufu.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Exceção personalizada como classe interna
    public static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String message) {
            super(message);
        }
    }


    // Outra exceção personalizada como classe interna
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }


    // Handler para a exceção TokenValidationException
    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<?> handleTokenValidationException(TokenValidationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                "UNAUTHORIZED"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


    // Handler para a exceção ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                "NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    // Handler para exceções gerais
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {

        String message = "Um erro interno ocorreu. Por favor, contate o suporte.";

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                message,
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    // Handler para DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String message = "Falha na validação dos dados: " + ex.getMostSpecificCause().getMessage();
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                message,
                request.getDescription(false),
                "DATA_INTEGRITY_VIOLATION"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



}
