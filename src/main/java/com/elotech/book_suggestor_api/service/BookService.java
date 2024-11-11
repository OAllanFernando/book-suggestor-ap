package com.elotech.book_suggestor_api.service;

import com.elotech.book_suggestor_api.exception.BookException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.repository.BookRepository;
import com.elotech.book_suggestor_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) throws BookException {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookException(StandardResponse.BOOK_ALREADY_EXISTS);
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) throws BookException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookException(StandardResponse.BOOK_NOT_FOUND));
    }

    public Book updateBook(Long id, Book updatedBook) throws BookException {
        Book book = getBookById(id);

        if (updatedBook.getTitle() != null) {
            book.setTitle(updatedBook.getTitle());
        }

        if (updatedBook.getAuthor() != null) {
            book.setAuthor(updatedBook.getAuthor());
        }

        if (updatedBook.getCategory() != null) {
            book.setCategory(updatedBook.getCategory());
        }

        if (updatedBook.getIsbn() != null) {
            book.setIsbn(updatedBook.getIsbn());
        }

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) throws BookException {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public Book getBookByIsbn(String isbn) throws BookException {
        Book book = bookRepository.findByIsbn(isbn);
        if(book == null) {
            throw new BookException(StandardResponse.BOOK_NOT_FOUND);
        }
        return book;
    }
}
