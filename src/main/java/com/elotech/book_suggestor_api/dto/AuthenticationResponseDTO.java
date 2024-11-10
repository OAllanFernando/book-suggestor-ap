package com.elotech.book_suggestor_api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationResponseDTO(String token){
}