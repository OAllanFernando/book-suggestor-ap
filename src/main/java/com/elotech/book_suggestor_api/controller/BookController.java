package com.elotech.book_suggestor_api.controller;

import com.elotech.book_suggestor_api.exception.BookException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.service.BookService;
import com.elotech.book_suggestor_api.utils.StandartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (BookException e) {
            return new ResponseEntity<>(new StandartResponse(e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (BookException e) {
            return new ResponseEntity<>(new StandartResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (BookException e) {
            return new ResponseEntity<>(new StandartResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(new StandartResponse("Book deleted successfully"), HttpStatus.NO_CONTENT);
        } catch (BookException e) {
            return new ResponseEntity<>(new StandartResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
