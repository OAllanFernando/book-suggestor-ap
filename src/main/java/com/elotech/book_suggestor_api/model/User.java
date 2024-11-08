package com.elotech.book_suggestor_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

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
    private LocalDate createdAt;

    @jakarta.validation.constraints.NotNull
    private String phoneNumber;
}
