package com.elotech.book_suggestor_api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.elotech.book_suggestor_api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.create().withIssuer("Book Suggestor API")
                   .withSubject(user.getEmail())
                   .withExpiresAt(getExpirationDate())
                   .sign(algorithm);
       } catch (JWTVerificationException e) {
           throw new RuntimeException("Error generating token", e);
       }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer().build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error validating token", e);
        }
    }

    private  Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
