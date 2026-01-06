package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(unique = true, nullable = false)
    private String isbn;

    private String autor;

    public Livro() {
        // Construtor vazio (obrigatório para o JPA)
    }

    public Livro(String titulo, String isbn, String autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

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
