package com.elotech.book_suggestor_api.service;

import com.elotech.book_suggestor_api.exception.BookException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.repository.BookRepository;
import com.elotech.book_suggestor_api.utils.StandartResponse;
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
            throw new BookException(StandartResponse.BOOK_NOT_FOUND);
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) throws BookException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookException(StandartResponse.BOOK_NOT_FOUND));
    }

    public Book updateBook(Long id, Book updatedBook) throws BookException {
        Book book = getBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) throws BookException {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
