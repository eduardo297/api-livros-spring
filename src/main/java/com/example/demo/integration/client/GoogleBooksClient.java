package com.example.demo.integration.client;


import com.example.demo.exeception.LivroNaoEncontradoNoGoogleException;
import com.example.demo.integration.dto.GoogleBookResponseDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleBooksClient {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private final RestClient restClient;

    public GoogleBooksClient() {
        this.restClient = RestClient.create(BASE_URL);
    }

    public GoogleBookResponseDTO buscarPorIsbn(String isbn) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", "isbn:" + isbn)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new LivroNaoEncontradoNoGoogleException("Erro na API do Google para o ISBN: " + isbn);
                })
                .body(GoogleBookResponseDTO.class);
    }
}
