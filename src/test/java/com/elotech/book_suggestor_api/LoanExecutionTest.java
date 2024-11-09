package com.elotech.book_suggestor_api;

import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.Loan;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.service.LoanService;
import com.elotech.book_suggestor_api.utils.StandartResponse;
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
        assertEquals(StandartResponse.LOAN_INCORRECT_RETURN_DATE, exceptionByWrongDate.getMessage());
    }


}
