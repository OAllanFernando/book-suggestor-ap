package com.elotech.book_suggestor_api.repository;

import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserIdAndStatus(Long userId, LoanStatus status);
    List<Loan> findByBookIdAndStatus(Long bookId, LoanStatus status);

    List<Loan> findAllByStatus(LoanStatus status);
    List<Loan> findAllByUserId(Long userId);
    List<Loan> findAllByBookId(Long bookId);

    List<Loan> findAllByCategory(String category);

    List<Loan> findAllUserPreferences(Long userId);

    
    List<Loan> findActiveLoanAndReturnDateIsAtMidnight();
    // Multiple validations
    List<Loan> findActiveLoanAndReturnDateIsAtMidnightAndStatusIsPending();
}
