package br.com.alura.bookworm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GutendexAPIService {
    @Value("${gutendex.api.url}")
    private String apiURL;

    public String searchBookByTitle(String bookTitle) throws RuntimeException {
        String url = apiURL + "?search=" + bookTitle.replaceAll(" ", "%20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ocorreu um Erro ao Realizar a Requisição", e);
        }

        return response.body();
    }
}
