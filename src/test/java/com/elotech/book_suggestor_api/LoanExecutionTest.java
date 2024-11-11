package com.elotech.book_suggestor_api;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.model.enums.LoanStatus;
import com.elotech.book_suggestor_api.repository.LoanRepository;
import com.elotech.book_suggestor_api.service.LoanService;
import com.elotech.book_suggestor_api.utils.StandardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// torna o mockito responsável por inicializar os mocks @ExtendWith(MockitoExtension.class)

@ExtendWith(MockitoExtension.class)
public class LoanExecutionTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    User user = new User("Allann", "Email@email", "(44) 99826-0968", "senha_forte");
    Book book = new Book("O teste do emprestimo", "Allan Tester", "UNIC_ISBN", LocalDate.now(), "Default Category");

    @Test
    void testLoanTimeCreation() throws LoanException {

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
                StandardResponse.LOAN_INCORRECT_RETURN_DATE);
    }

    @Test
    void mustNotLoanABookAlreadyLoaned() throws LoanException {

        book.setId(1L);
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setReturnDate(LocalDateTime.now().plusDays(1));

        when(loanRepository.findByBookIdAndStatus(book.getId(),  LoanStatus.ACTIVE)).thenReturn(Collections.singletonList(loan));

        LoanException exception = assertThrows(LoanException.class, () -> {
            this.loanService.createLoan(user, book);
        });

        assertEquals(StandardResponse.LOAN_BOOK_ALREADY_LOANED, exception.getMessage());
    }
}

/*
package com.elotech.book_suggestor_api;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.service.LoanService;
import com.elotech.book_suggestor_api.utils.StandardResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoanExecutionTest {

    Loan loan = new Loan();
    Book book = new Book();
    User user = new User();

    @BeforeEach
    void beforeTheTest() {
        book.setAuthor("Allan Tester");
        book.setCategory("Defalt Category");
        book.setIsbn("UNIC_ISBN");
        book.setPublicationDate(null);
        book.setTitle("Title");

        user.setCreatedAt(null);
        user.setEmail("Email");
        user.setName("Name");
        user.setPhoneNumber("PhoneNumber");

        loan.setBook(book);
        loan.setUser(user);
    }

    @AfterEach
    void afterTheTest() {
        book = null;
        user = null;
        loan = null;
    }

    @Test
    void TestTimeCreation() {

        LoanException exceptionByWrongDate = assertThrows(LoanException.class, () -> {
            LocalDate date = LocalDate.now();
            LocalDate dateAfter = LocalDate.now().plusDays(1);
            loan.setLoanDate(date);
            loan.setReturnDate(dateAfter);
            Loan neverloan = LoanService.createLoan(loan);
        });
        assertEquals(StandardResponse.LOAN_INCORRECT_RETURN_DATE, exceptionByWrongDate.getMessage());
    }


 */