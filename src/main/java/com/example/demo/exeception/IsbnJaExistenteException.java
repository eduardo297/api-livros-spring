package com.example.demo.exeception;

public class IsbnJaExistenteException extends RuntimeException {
    public IsbnJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
