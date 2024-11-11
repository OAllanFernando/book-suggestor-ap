package com.elotech.book_suggestor_api;

import com.elotech.book_suggestor_api.exception.BookException;
import com.elotech.book_suggestor_api.exception.LoanException;
import com.elotech.book_suggestor_api.exception.UserException;
import com.elotech.book_suggestor_api.model.Book;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.repository.BookRepository;
import com.elotech.book_suggestor_api.repository.UserRepository;
import com.elotech.book_suggestor_api.service.BookService;
import com.elotech.book_suggestor_api.service.UserService;
import com.elotech.book_suggestor_api.utils.StandardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserExecutionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void varifyDuplicatedEmail() throws UserException {

        User user = new User("ONovoUser", "allan@email.com", "(44) 99855-5500", "SENHAA");

        Mockito.when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.createUser(user);
        });

        assertEquals(StandardResponse.USER_EMAIL_ALREADY_EXISTS, exception.getMessage());
    }
}
