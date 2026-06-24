package com.bajaj.bfhl.exception;

import com.bajaj.bfhl.dto.BfhlErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BfhlErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = "Invalid JSON input: " + ex.getMostSpecificCause().getMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new BfhlErrorResponse(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlErrorResponse> handleGeneralException(Exception ex) {
        String message = "An error occurred: " + ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BfhlErrorResponse(message));
    }
}
