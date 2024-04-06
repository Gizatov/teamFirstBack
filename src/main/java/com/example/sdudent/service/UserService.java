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
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<User> getAllCandidates() {
        return userRepository.findAllCandidate();
    }

    public User getUserById(Long id) {
        return userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
    }

    public User getCandidateById(Long id){
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }
}

