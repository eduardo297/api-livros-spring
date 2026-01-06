package com.example.demo.exeception;

public class LivroNaoEncontradoNoGoogleException extends RuntimeException {
    public LivroNaoEncontradoNoGoogleException(String message) {
        super(message);
    }
}
