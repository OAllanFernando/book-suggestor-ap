package com.elotech.book_suggestor_api.service;

import com.elotech.book_suggestor_api.dto.GoogleBooksResponseDTO;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.utils.RestClientUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBooksService {

    @Value("${api.security.api_key_books}")
    private String API_KEY;

    private final RestClientUtils restClientUtils;

    public GoogleBooksService() {
        this.restClientUtils = new RestClientUtils("https://www.googleapis.com/books/v1/volumes");
    }

    public List<Book> searchBooks(String query) throws Exception {
        List<String> headers = new ArrayList<>();
        headers.add("Content-Type");
        headers.add("application/json");
        headers.add("Accept");
        headers.add("application/json");

        String encodedTitle = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String uri = "?q=" + encodedTitle + "&key=" + API_KEY;

        HttpResponse<String> response = restClientUtils.get(uri, headers);

        return parseGoogleBooksResponse(response);
    }

    private List<Book> parseGoogleBooksResponse(HttpResponse<String> response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch books: " + response.statusCode());
        }

        GoogleBooksResponseDTO googleBooksResponse = objectMapper.readValue(response.body(), GoogleBooksResponseDTO.class);

        List<Book> books = new ArrayList<>();

        // Definir o formato esperado para a data de publicação
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (GoogleBooksResponseDTO.GoogleBookItem item : googleBooksResponse.getItems()) {
            GoogleBooksResponseDTO.GoogleBookVolume volumeInfo = item.getVolumeInfo();

            String author = volumeInfo.getAuthors() != null && !volumeInfo.getAuthors().isEmpty() ? volumeInfo.getAuthors().get(0) : "";
            String isbn = volumeInfo.getIndustryIdentifiers() != null && !volumeInfo.getIndustryIdentifiers().isEmpty()
                    ? volumeInfo.getIndustryIdentifiers().get(0).getIdentifier() : "";

            LocalDate publicationDate = null;
            if (volumeInfo.getPublishedDate() != null && !volumeInfo.getPublishedDate().isEmpty()) {
                try {
                    publicationDate = LocalDate.parse(volumeInfo.getPublishedDate(), dateFormatter);
                } catch (Exception e) {
                    publicationDate = LocalDate.now();
                }
            }

            // Passando a data de publicação para o construtor de Book
            Book book = new Book(volumeInfo.getTitle(), author, isbn, publicationDate, "Google Books");
            books.add(book);
        }

        return books;
    }

}
