package com.example.demo.controller;

import com.example.demo.DTO.LivroRequestDTO;
import com.example.demo.DTO.LivroResponseDTO;
import com.example.demo.service.LivroService;
import jakarta.validation.Valid;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // GET /livros

    @GetMapping

    public Page<LivroResponseDTO> listarLivros( @RequestParam(required = false) String titulo,
                                                @RequestParam(required = false) String autor,Pageable pageable) {
        return livroService.listarLivros(titulo, autor, pageable);
    }

    // GET /livros/{id}
    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    // POST /livros
    @PostMapping
    public LivroResponseDTO criarLivro(
            @RequestBody @Valid LivroRequestDTO dto
    ) {
        return livroService.criar(dto);
    }
    // POST /importar/{isbn}
    @PostMapping("/importar/{isbn}")
    public LivroResponseDTO importarPorIsbn(@PathVariable String isbn) {
        return livroService.importarPorIsbn(isbn);
    }

    // PUT /livros/{id}
    @PutMapping("/{id}")
    public LivroResponseDTO atualizarLivro(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequestDTO dto
    ) {
        return livroService.atualizar(id, dto);
    }

    // DELETE /livros/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarLivro(@PathVariable Long id) {
        livroService.deletar(id);
    }
}