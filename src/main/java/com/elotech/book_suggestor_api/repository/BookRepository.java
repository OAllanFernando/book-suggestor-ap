package com.elotech.book_suggestor_api.repository;

import com.elotech.book_suggestor_api.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(@NotNull(message = "ISBN cannot be null") @NotBlank(message = "ISBN cannot be blank") String isbn);
}
