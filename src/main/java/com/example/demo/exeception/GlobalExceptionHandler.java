package com.example.demo.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            LivroNaoEncontradoException.class,
            LivroNaoEncontradoNoGoogleException.class
    })
    public ResponseEntity<String> tratarNotFound(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IsbnJaExistenteException.class)
    public ResponseEntity<String> tratarIsbnDuplicado(
            IsbnJaExistenteException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}