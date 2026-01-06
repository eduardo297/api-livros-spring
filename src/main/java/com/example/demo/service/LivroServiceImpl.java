package com.example.demo.service;

import com.example.demo.DTO.LivroRequestDTO;
import com.example.demo.DTO.LivroResponseDTO;
import com.example.demo.entity.Livro;
import com.example.demo.integration.dto.GoogleBookItemDTO;
import com.example.demo.integration.dto.GoogleBookResponseDTO;
import com.example.demo.integration.dto.GoogleVolumeInfoDTO;
import com.example.demo.repository.LivroRepository;

import com.example.demo.integration.client.GoogleBooksClient;

import jakarta.validation.constraints.Null;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.exeception.*;



@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final GoogleBooksClient googleBooksClient;

    public LivroServiceImpl(LivroRepository livroRepository,GoogleBooksClient googleBooksClient) {
        this.livroRepository = livroRepository;
        this.googleBooksClient=googleBooksClient;
    }

    private Livro convertToLivro(LivroRequestDTO dto){
        return new Livro(
                dto.getTitulo(),
                dto.getIsbn(),
                dto.getAutor());
    }
    private LivroResponseDTO toResponse(Livro livro) {
        return new LivroResponseDTO(
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAutor(),
                livro.getId()
        );
    }
    private Livro fromGoogle(GoogleBookResponseDTO dto){
        if (dto == null || dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new LivroNaoEncontradoNoGoogleException("Livro não encontrado no google api");
        }
        GoogleBookItemDTO googleItem=dto.getItems().getFirst();
        GoogleVolumeInfoDTO bookvolume = googleItem.getVolumeInfo();

        String autor;
        if(bookvolume.getAuthors()==null){
            autor="desconhecido";
        }else{
            autor = bookvolume.getAuthors().getFirst();
        }
       return new Livro(
               bookvolume.getTitle(),
               null,
               autor
       );
    }

    @Override
    public Page<LivroResponseDTO> listarLivros(String titulo, String autor,Pageable pageable) {
        Page<Livro> pageLivros;
        if (titulo == null && autor == null) {
            pageLivros = livroRepository.findAll(pageable);
        }
        else if (titulo != null && autor != null) {
            pageLivros= livroRepository.findByTituloAndAutor(titulo,autor,pageable);
        }
        else if (titulo != null) {
            pageLivros=livroRepository.findByTituloContainingIgnoreCase(titulo,pageable);
        }
        else {
            pageLivros=livroRepository.findByAutorContainingIgnoreCase(autor,pageable);
        }
        return pageLivros.map(this::toResponse);
    }

    @Override
    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro= livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(
                        "Livro não encontrado"
                ));
        return toResponse(livro);
    }
    @Override
    public LivroResponseDTO importarPorIsbn(String isbn){
        if (livroRepository.existsByIsbn(isbn)) {
            throw new IsbnJaExistenteException("Já existe um livro com esse ISBN");
        }
        GoogleBookResponseDTO googleResponse = googleBooksClient.buscarPorIsbn(isbn);
        Livro livro=fromGoogle(googleResponse);
        livro.setIsbn(isbn);
        livroRepository.save(livro);
        return toResponse(livro);
    }


    @Override
    public LivroResponseDTO criar(LivroRequestDTO livro) {
        if (livroRepository.existsByIsbn(livro.getIsbn())) {
            throw new IsbnJaExistenteException("Já existe um livro com esse ISBN");
        }
        Livro newlivro=convertToLivro(livro);
        livroRepository.save(newlivro);
        return toResponse(newlivro);
    }

    @Override
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO novolivro) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(
                "Livro não encontrado"
        ));
        if (livroRepository.existsByIsbnAndIdNot(novolivro.getIsbn(),id)) {
            throw new IsbnJaExistenteException("Já existe um livro com esse ISBN");
        }

        livroExistente.setTitulo(novolivro.getTitulo());
        livroExistente.setAutor(novolivro.getAutor());
        livroExistente.setIsbn(novolivro.getIsbn());

        livroRepository.save(livroExistente);
        return toResponse(livroExistente);

    }

    @Override
    public void deletar(Long id) {
        livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(
                        "Livro não encontrado"
                ));
        livroRepository.deleteById(id);
    }
}