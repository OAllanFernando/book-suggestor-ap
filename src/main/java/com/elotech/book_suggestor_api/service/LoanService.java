package com.elotech.book_suggestor_api.service;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.model.enums.LoanStatus;
import com.elotech.book_suggestor_api.repository.LoanRepository;
import com.elotech.book_suggestor_api.utils.StandartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(User user, Book book) throws LoanException {
        Optional<Loan> activeLoan = loanRepository.findByBookIdAndStatus(book.getId(), LoanStatus.ACTIVE).stream().findFirst();

        if (activeLoan.isPresent()) {
            throw new LoanException(StandartResponse.LOAN_BOOK_ALREADY_LOANED);
        }
        Loan loan = new Loan(user, book);

//        if (loan.getReturnDate() != null && !loan.getReturnDate().isBefore(loan.getLoanDate())) {
//            throw new LoanException(StandartResponse.LOAN_INCORRECT_RETURN_DATE);
//        }
        return loanRepository.save(loan);
    }

    public Loan getLoanById(Long loanId) throws LoanException {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException(StandartResponse.LOAN_NOT_FOUND));
    }


    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan returnedLoan(Long loanId) throws LoanException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException(StandartResponse.LOAN_NOT_FOUND));

        loan.setReturnDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.RETURNED);
        return loanRepository.save(loan);
    }

    // TODO: Implment logs for tracking loan reactivation
    // SOLUTION : plus 1 minute. That leaves to track every loan reactivation
    public Loan reactivateLoan(Long loanId) throws LoanException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException(StandartResponse.LOAN_NOT_FOUND));

        loan.setReturnDate(loan.getLoanDate().toLocalDate().atStartOfDay().plusMinutes(1));
        loan.setStatus(LoanStatus.ACTIVE);
        return loanRepository.save(loan);
    }


}
