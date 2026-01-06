package com.example.demo.repository;

import com.example.demo.entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LivroRepository extends JpaRepository<Livro, Long> {


    Page<Livro> findByTituloContainingIgnoreCase(
            String titulo,
            Pageable pageable
    );

    Page<Livro> findByAutorContainingIgnoreCase(
            String autor,
            Pageable pageable
    );

    Page<Livro> findByTituloAndAutor(
            String titulo,
            String autor,
            Pageable pageable
    );

    boolean existsByIsbn(String isbn);
    boolean existsByIsbnAndIdNot(String isbn, Long id);
}
