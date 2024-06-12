package com.example.sdudent.controller;

import com.example.sdudent.dto.StatisticDto;
import com.example.sdudent.entity.StudentVoice;
import com.example.sdudent.entity.User;
import com.example.sdudent.service.StatisticService;
import com.example.sdudent.service.StudentVoiceService;
import com.example.sdudent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/student")
public class StudentController {

    private final StudentVoiceService studentVoiceService;
    private final UserService userService;
    private final StatisticService statisticService;

    @Autowired
    public StudentController(StudentVoiceService studentVoiceService, UserService userService, StatisticService statisticService) {
        this.studentVoiceService = studentVoiceService;
        this.userService = userService;
        this.statisticService = statisticService;
    }

    // Vote for a candidate
    @PostMapping("/vote/{candidateId}")
    public ResponseEntity<String> voteForCandidate(@PathVariable Long candidateId) {
        // Get the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Check if the current user is not a candidate
        if (currentUser.getRole().getRole_name().equals("candidate")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Кандидаты не могут голосовать.");
        }

        // Create a record of the vote
        StudentVoice studentVoice = new StudentVoice();
        studentVoice.setStudent(currentUser);

        // Get the candidate user by their ID
        User candidate = userService.getUserById(candidateId);
        if (candidate == null) {
            return ResponseEntity.notFound().build();
        }
        studentVoice.setCandidate(candidate);

        // Save the vote record
        studentVoiceService.saveStudentVoice(studentVoice);

        return ResponseEntity.ok("Голос за кандидата успешно засчитан.");
    }

    // Check if the current user exists
    @GetMapping("/currentUserExists")
    public ResponseEntity<Map<String, Boolean>> checkIfCurrentUserExists() {
        // Get the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Check if the current user exists in the database
        boolean exists = studentVoiceService.checkIfStudentExists(currentUser.getId());

        // Construct a JSON object with the key "isExist"
        Map<String, Boolean> response = new HashMap<>();
        response.put("isExist", exists);

        return ResponseEntity.ok(response);
    }

    // Get statistics
    @GetMapping("/statistics")
    public ResponseEntity<StatisticDto> getStatistic() {
        StatisticDto statisticDto = statisticService.getStatistic();
        return ResponseEntity.ok().body(statisticDto);
    }


}
