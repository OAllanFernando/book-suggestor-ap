package com.elotech.book_suggestor_api.service;


import com.elotech.book_suggestor_api.exception.UserException;
import com.elotech.book_suggestor_api.model.User;
import com.elotech.book_suggestor_api.repository.UserRepository;
import com.elotech.book_suggestor_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws UserException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserException(StandardResponse.USER_EMAIL_ALREADY_EXISTS);
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(StandardResponse.USER_NOT_FOUND));
    }

    public User updateUser(Long id, User updatedUser) throws UserException {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws UserException {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
