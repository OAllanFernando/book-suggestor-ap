package com.elotech.book_suggestor_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Table(name = "books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Author cannot be null")
    @NotBlank(message = "Author cannot be blank")
    private String author;

    @NotNull(message = "ISBN cannot be null")
    @NotBlank(message = "ISBN cannot be blank")
    @Column(unique = true)
    private String isbn;

    @NotNull(message = "Publication date cannot be null")
    private LocalDate publicationDate;

    @NotNull(message = "Category cannot be null")
    @NotBlank(message = "Category cannot be blank")
    private String category;

    public Book() {

    }

    public Book( String title, String author, String isbn, LocalDate publicationDate, String category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.category = category;
    }
}
