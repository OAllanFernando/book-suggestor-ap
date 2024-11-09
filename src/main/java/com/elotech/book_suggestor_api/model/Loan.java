package com.elotech.book_suggestor_api.model;

import com.elotech.book_suggestor_api.model.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull
    private LocalDateTime  loanDate;

    @NotNull
    private LocalDateTime  returnDate;

    @Column(nullable = false)
    private LoanStatus status;



    public Loan(User user, Book book) {
        this.user = user;
        this.book = book;
        this.loanDate = LocalDateTime.now();
        this.status = LoanStatus.ACTIVE;
        // this.returnDate =  LocalDate.now();
        this.returnDate = LocalDateTime.now().toLocalDate().atStartOfDay();
    }
}
