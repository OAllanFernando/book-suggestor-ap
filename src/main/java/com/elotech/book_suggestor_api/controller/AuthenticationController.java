package com.elotech.book_suggestor_api.controller;

import com.elotech.book_suggestor_api.config.TokenService;
import com.elotech.book_suggestor_api.dto.AuthenticationDTO;
import com.elotech.book_suggestor_api.dto.AuthenticationResponseDTO;
import com.elotech.book_suggestor_api.dto.RegisterDTO;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.service.UserService;
import com.elotech.book_suggestor_api.utils.StandardResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid AuthenticationDTO user) {
        try {
            var emailPassword = new UsernamePasswordAuthenticationToken(user.email(), user.password());
            var authenticatedUser = this.authenticationManager.authenticate(emailPassword);

            var token = tokenService.generateToken((User) authenticatedUser.getPrincipal());
            AuthenticationResponseDTO response = new AuthenticationResponseDTO(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO user) {
        try {
            userService.findUserByEmail(user.email());

            String encodedPassword = new BCryptPasswordEncoder().encode(user.password());
            userService.createUser(new User(user.name(), user.email(), user.phoneNumber(), encodedPassword));

            return ResponseEntity.status(HttpStatus.CREATED).body(StandardResponse.USER_CREATED);

        } catch (Exception e) {
            if (e.getMessage().equals(StandardResponse.USER_EMAIL_ALREADY_EXISTS)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StandardResponse.INTERNAL_SERVER_ERROR);
        }
    }
}
