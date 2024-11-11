package com.elotech.book_suggestor_api.controller;

import com.elotech.book_suggestor_api.exception.BookException;
import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.service.BookService;
import com.elotech.book_suggestor_api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;
    private final BookService bookService;

    @Autowired
    public LoanController(LoanService loanService, BookService bookService) {
        this.loanService = loanService;
        this.bookService = bookService;
    }

    @PostMapping("/create/{isbn}")
    public Loan createLoan(@PathVariable String isbn) throws LoanException, BookException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();
        Book book = bookService.getBookByIsbn(isbn);
        return loanService.createLoan(loggedInUser, book);
    }

    @GetMapping("/{loanId}")
    public Loan getLoanById(@PathVariable Long loanId) throws LoanException {
        return loanService.getLoanById(loanId);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/{loanId}/return")
    public Loan returnLoan(@PathVariable Long loanId) throws LoanException {
        return loanService.returnedLoan(loanId);
    }

    @PostMapping("/{loanId}/reactivate")
    public Loan reactivateLoan(@PathVariable Long loanId) throws LoanException {
        return loanService.reactivateLoan(loanId);
    }

    @GetMapping("/recommend")
    public List<Book> getRecommendsByUser() throws LoanException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();
        return loanService.recommendBooks(loggedInUser);
    }
}
