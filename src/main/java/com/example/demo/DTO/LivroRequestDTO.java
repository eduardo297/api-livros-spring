package com.example.demo.DTO;

public class LivroRequestDTO {

    private String titulo;
    private String isbn;
    private String autor;

    // construtor vazio
    public LivroRequestDTO() {
    }

    // construtor opcional
    public LivroRequestDTO(String titulo, String isbn, String autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
    }

    // getters
    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

    // setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}