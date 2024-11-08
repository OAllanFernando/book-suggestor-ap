package com.elotech.book_suggestor_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.validation.constraints.NotNull
    private String name;

    @NotNull
    @jakarta.validation.constraints.Email
    @Column(unique = true)
    private String email;

    @NotNull
    private LocalDate created_at;

    @jakarta.validation.constraints.NotNull
    private String phone_number;
}
