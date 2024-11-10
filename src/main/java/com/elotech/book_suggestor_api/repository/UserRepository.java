package com.elotech.book_suggestor_api.repository;

import com.elotech.book_suggestor_api.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(@NotNull @Email String email);

    Optional<User> findByEmail(String email);
}
