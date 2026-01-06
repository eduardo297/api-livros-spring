package com.example.demo.exeception;

public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}