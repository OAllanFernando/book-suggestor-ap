package com.elotech.book_suggestor_api;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.repository.LoanRepository;
import com.elotech.book_suggestor_api.service.LoanService;
import com.elotech.book_suggestor_api.utils.StandartResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanExecutionTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;



    @Test
    void testLoanTimeCreation() throws LoanException {

        User user = new User();
        user.setEmail("Email");
        user.setName("Name");
        user.setPhoneNumber("PhoneNumber");

        Book book = new Book();
        book.setAuthor("Allan Tester");
        book.setCategory("Default Category");
        book.setIsbn("UNIC_ISBN");
        book.setTitle("Title");

        LocalDateTime returnDate = LocalDateTime.now().toLocalDate().atStartOfDay();
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setReturnDate(returnDate);

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan newLoan = this.loanService.createLoan(user, book);

        verify(loanRepository, times(1)).save(any(Loan.class));

        assertTrue(newLoan.getReturnDate().isBefore(newLoan.getLoanDate()),
                StandartResponse.LOAN_INCORRECT_RETURN_DATE);
    }
}