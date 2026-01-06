package com.example.demo.DTO;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String isbn;
    private String autor;

    public LivroResponseDTO(String titulo,String isbn,String autor,long id){
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.id=id;
    }

}