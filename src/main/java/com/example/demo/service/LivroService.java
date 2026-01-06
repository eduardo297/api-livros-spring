package com.example.demo.service;

import com.example.demo.DTO.LivroRequestDTO;
import com.example.demo.DTO.LivroResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


public interface LivroService {

    Page<LivroResponseDTO> listarLivros(String titulo,
                                        String autor,Pageable pageable);
    LivroResponseDTO importarPorIsbn(String isbn);

    LivroResponseDTO buscarPorId(Long id);

    LivroResponseDTO criar(LivroRequestDTO livro);

    LivroResponseDTO atualizar(Long id, LivroRequestDTO livro);

    void deletar(Long id);
}
