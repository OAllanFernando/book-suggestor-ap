package com.elotech.book_suggestor_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDTO (

        @NotNull(message = "Name is required")
        String name,

        @NotNull(message = "Phone number is required")
        String phoneNumber,

        @NotNull(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password
) {}
