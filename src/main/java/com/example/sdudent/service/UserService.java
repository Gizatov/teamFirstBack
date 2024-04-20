package com.example.sdudent.service;

import com.example.sdudent.entity.User;
import com.example.sdudent.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Method to create a new user and save it to the database
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        // Method to retrieve all users from the database
        return userRepository.findAll();
    }

    public List<User> getAllCandidates() {
        // Method to retrieve all candidates from the database
        return userRepository.findAllCandidate();
    }

    public User getUserById(Long id) {
        // Method to retrieve a user by their ID from the database
        return userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
    }

    public User getCandidateById(Long id){
        // Method to retrieve a candidate by their ID from the database
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        // Method to update a user's information in the database
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        // Method to delete a user by their ID from the database
        userRepository.deleteById(Math.toIntExact(id));
    }
}
