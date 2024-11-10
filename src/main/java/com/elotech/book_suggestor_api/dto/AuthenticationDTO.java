package com.elotech.book_suggestor_api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "E-mail cannot be blank") String email,
        @NotBlank(message = "Password cannot be blank") String password) {
}