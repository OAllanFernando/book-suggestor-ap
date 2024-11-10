package com.elotech.book_suggestor_api.controller;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/create")
    public Loan createLoan(@RequestBody User user, @RequestBody Book book) throws LoanException {
        return loanService.createLoan(user, book);
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
}
