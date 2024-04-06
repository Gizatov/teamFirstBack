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

    private final UserService userService;
    private final StudentVoiceService studentVoiceService;
    private final StudentVoiceRepository studentVoiceRepository;

    public UserController(UserService userService, StudentVoiceService studentVoiceService, StudentVoiceRepository studentVoiceRepository) {
        this.userService = userService;
        this.studentVoiceService = studentVoiceService;
        this.studentVoiceRepository = studentVoiceRepository;
    }

    // Метод создания нового пользователя
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/candidates")
    public ResponseEntity<List<User>> getAllCandidates() {

        List<User> users = userService.getAllCandidates();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/result")
    public ResponseEntity<List<CandidatesResultDto>> getFinalCandidates() {
        List<Object[]> results = studentVoiceRepository.countVotesByCandidateId();

        List<CandidatesResultDto> candidatesResultDtos = results.stream()
                .map(objArray -> {
                    Long candidateId = (Long) objArray[0]; // candidate_id
                    Long votesCount = (Long) objArray[1]; // votes_count

                    // Получаем пользователя по candidateId из таблицы пользователей
                    User candidate = userService.getCandidateById(candidateId);
                    if (candidate != null) {
                        String candidateName = candidate.getName();
                        String candidateLastName = candidate.getLastName();

                        return new CandidatesResultDto(candidateId, votesCount, candidateName, candidateLastName);
                    } else {
                        // Если пользователь не найден, создаем DTO только с candidateId и votesCount
                        return new CandidatesResultDto(candidateId, votesCount, null, null);
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(candidatesResultDtos);
    }
    @GetMapping("/candidate")
    public ResponseEntity<User> getChoiceCandidate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Long candidateId = studentVoiceService.getCandidate(currentUser.getId());
        User candidate = userService.getCandidateById(candidateId);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

