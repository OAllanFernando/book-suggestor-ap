package com.elotech.book_suggestor_api;

import com.elotech.book_suggestor_api.exception.BookException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.repository.BookRepository;
import com.elotech.book_suggestor_api.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookExecutionTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void mustListAllBooks() throws BookException {

        Book book = new Book("O teste do emprestimo", "Allan Tester", "UNIC_ISBN", LocalDate.now(), "Default Category");

        Mockito.when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        List<Book> books = this.bookService.getAllBooks();

        verify(bookRepository, times(1)).findAll();

        assertEquals(1, books.size());
    }
}
