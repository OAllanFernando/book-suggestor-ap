package com.elotech.book_suggestor_api.repository;

import com.elotech.book_suggestor_api.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(@NotNull(message = "ISBN cannot be null") @NotBlank(message = "ISBN cannot be blank") String isbn);

    @Query("SELECT DISTINCT b FROM Book b " +
            "LEFT JOIN Loan l ON b.id = l.book.id AND l.user.id = ?2 " +
            "WHERE b.category IN ?1 AND l.id IS NULL")
    List<Book> findAvailableBooksByCategory(List<String> categories, Long userId);

    @Query("SELECT DISTINCT b FROM Book b WHERE b.isbn LIKE %?1%")
    Book findByIsbn(String isbn);
}
