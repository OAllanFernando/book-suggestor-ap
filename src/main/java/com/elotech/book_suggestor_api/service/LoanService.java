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

import java.time.LocalDate;
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
}
