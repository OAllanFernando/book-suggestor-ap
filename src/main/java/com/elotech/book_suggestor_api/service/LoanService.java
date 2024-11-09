package com.elotech.book_suggestor_api.service;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Loan;

public class LoanService {
    public static Loan createLoan(Loan loan) throws LoanException {
            throw new LoanException("The return date must be before the loan date");
    }
}
