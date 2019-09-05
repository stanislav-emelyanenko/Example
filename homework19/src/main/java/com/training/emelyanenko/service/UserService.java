package com.training.emelyanenko.service;

import com.training.emelyanenko.domain.User;
import com.training.emelyanenko.exception.InvalidArgumentException;
import com.training.emelyanenko.exception.UserNotFoundException;
import com.training.emelyanenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns the user from user repository if it was created.
     * Creates and returns the user from user repository if it wasn't created.
     *
     * @param customer it's customer name.
     * @return user from user repository.
     */
    public User createOrGet(String customer) {
        if (customer == null || customer.equals("")) {
            throw new InvalidArgumentException("Customer name can't be null");
        }
        Optional<User> maybeUser = userRepository.getByName(customer);
        if (!maybeUser.equals(Optional.empty()) && maybeUser.isPresent()) {
            return maybeUser.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
