package com.elotech.book_suggestor_api.service;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.model.enums.LoanStatus;
import com.elotech.book_suggestor_api.repository.BookRepository;
import com.elotech.book_suggestor_api.repository.LoanRepository;
import com.elotech.book_suggestor_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public Loan createLoan(User user, Book book) throws LoanException {
        Optional<Loan> activeLoan = loanRepository.findByBookIdAndStatus(book.getId(), LoanStatus.ACTIVE).stream().findFirst();

        if (activeLoan.isPresent()) {
            throw new LoanException(StandardResponse.LOAN_BOOK_ALREADY_LOANED);
        }
        Loan loan = new Loan(user, book);
        return loanRepository.save(loan);
    }

    public Loan getLoanById(Long loanId) throws LoanException {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException(StandardResponse.LOAN_NOT_FOUND));
    }


    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan returnedLoan(Long loanId) throws LoanException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException(StandardResponse.LOAN_NOT_FOUND));
        loan.setReturnDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.RETURNED);
        return loanRepository.save(loan);
    }

    // TODO: Implment logs for tracking loan reactivation
    // SOLUTION : plus 1 minute. That leaves to track every loan reactivation
    public Loan reactivateLoan(Long loanId) throws LoanException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException(StandardResponse.LOAN_NOT_FOUND));

        loan.setReturnDate(loan.getLoanDate().toLocalDate().atStartOfDay().plusMinutes(1));
        loan.setStatus(LoanStatus.ACTIVE);
        return loanRepository.save(loan);
    }


    public List<Book> recommendBooks(User user) throws LoanException {
        List<String> categories = loanRepository.findCategoriesByUser(user.getId());
        if (categories.isEmpty()) {
            throw new LoanException(StandardResponse.LOAN_NO_RECOMMENDATION);
        }
        List<Book> books = bookRepository.findAvailableBooksByCategory(categories, user.getId());
        return books;
    }
}
