package com.elotech.book_suggestor_api.controller;

import com.elotech.book_suggestor_api.dto.RegisterDTO;
import com.elotech.book_suggestor_api.exception.UserException;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody @Valid RegisterDTO user) throws UserException {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.password());
        userService.createUser(new User(user.name(), user.email(), user.phoneNumber(), encodedPassword));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws UserException {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) throws UserException {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws UserException {
        userService.deleteUser(id);
    }
}
