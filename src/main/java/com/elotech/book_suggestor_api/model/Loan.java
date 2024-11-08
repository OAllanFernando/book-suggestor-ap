package com.elotech.book_suggestor_api.model;

import com.elotech.book_suggestor_api.model.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

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
    private LocalDate loanDate;

    @NotNull
    private LocalDate returnDate;

    @Column(nullable = false)
    private LoanStatus status;



    public Loan(User user, Book book) {
        this.user = user;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.status = LoanStatus.ACTIVE;
        // com a data padronizada poderiaos apagar o status da tabela de emprestimos, economiza um enum e um campo em cada registro
        // this.returnDate =  LocalDate.now();
        this.returnDate = LocalDate.from(LocalDate.now().atStartOfDay());
    }
}
