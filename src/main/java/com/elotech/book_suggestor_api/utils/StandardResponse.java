package com.elotech.book_suggestor_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class StandardResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";


    public static final String LOAN_INCORRECT_RETURN_DATE = "The return date must be before the loan date";
    public static final String LOAN_BOOK_ALREADY_LOANED = "Book already loaned";
    public static final String LOAN_BOOK_NOT_FOUND = "Book not found";
    public static final String LOAN_NOT_FOUND = "Loan not found";
    public static final String LOAN_USER_NOT_FOUND = "User not found";
    public static final String LOAN_USER_ALREADY_LOANED = "User already loaned a book";
    public static final String LOAN_USER_NOT_LOANED = "User not loaned a book";


    public static final String USER_EMAIL_ALREADY_EXISTS = "Email already exists";


    public static final String BOOK_NOT_FOUND = "Book not found";

    public static final String USER_NOT_FOUND = "User not found";

}
