package com.example.sdudent.controller;

import com.example.sdudent.dto.CandidatesResultDto;
import com.example.sdudent.entity.User;
import com.example.sdudent.repository.StudentVoiceRepository;
import com.example.sdudent.service.StudentVoiceService;
import com.example.sdudent.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserService userService; // User service for user-related operations
    private final StudentVoiceService studentVoiceService; // Service for student voice-related operations
    private final StudentVoiceRepository studentVoiceRepository; // Repository for student voice data access

    public UserController(UserService userService, StudentVoiceService studentVoiceService, StudentVoiceRepository studentVoiceRepository) {
        this.userService = userService;
        this.studentVoiceService = studentVoiceService;
        this.studentVoiceRepository = studentVoiceRepository;
    }

    // Method to create a new user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user); // Create user using UserService
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // Return ResponseEntity with created user
    }

    // Method to retrieve all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Retrieve all users using UserService
        return ResponseEntity.ok(users); // Return ResponseEntity with all users
    }

    // Method to retrieve all candidates
    @GetMapping("/candidates")
    public ResponseEntity<List<User>> getAllCandidates() {
        List<User> users = userService.getAllCandidates(); // Retrieve all candidates using UserService
        return ResponseEntity.ok(users); // Return ResponseEntity with all candidates
    }

    // Method to retrieve final candidates with vote counts
    @GetMapping("/result")
    public ResponseEntity<List<CandidatesResultDto>> getFinalCandidates() {
        List<Object[]> results = studentVoiceRepository.countVotesByCandidateId(); // Retrieve vote counts for candidates from repository

        List<CandidatesResultDto> candidatesResultDtos = results.stream()
                .map(objArray -> {
                    Long candidateId = (Long) objArray[0]; // Get candidate ID from result object array
                    Long votesCount = (Long) objArray[1]; // Get vote count from result object array

                    // Get candidate user by ID from UserService
                    User candidate = userService.getCandidateById(candidateId);
                    if (candidate != null) {
                        String candidateName = candidate.getName();
                        String candidateLastName = candidate.getLastName();

                        return new CandidatesResultDto(candidateId, votesCount, candidateName, candidateLastName);
                    } else {
                        // If candidate not found, create DTO with candidateId and votesCount only
                        return new CandidatesResultDto(candidateId, votesCount, null, null);
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(candidatesResultDtos); // Return ResponseEntity with candidate result DTOs
    }

    // Method to retrieve the chosen candidate by the current user
    @GetMapping("/candidate")
    public ResponseEntity<User> getChoiceCandidate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Long candidateId = studentVoiceService.getCandidate(currentUser.getId()); // Get chosen candidate ID by current user
        User candidate = userService.getCandidateById(candidateId); // Get candidate user by ID
        return ResponseEntity.ok(candidate); // Return ResponseEntity with chosen candidate
    }

    // Method to retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id); // Retrieve user by ID using UserService
        return ResponseEntity.ok(user); // Return ResponseEntity with user
    }

    // Method to update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user); // Update user using UserService
        return ResponseEntity.ok(updatedUser); // Return ResponseEntity with updated user
    }

    // Method to delete a user
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id); // Delete user by ID using UserService
        return ResponseEntity.noContent().build(); // Return ResponseEntity with no content
    }
}

