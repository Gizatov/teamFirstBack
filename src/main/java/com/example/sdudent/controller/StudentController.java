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

    @PostMapping("/vote/{candidateId}")
    public ResponseEntity<String> voteForCandidate(@PathVariable Long candidateId) {
        // Получаем текущего пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Проверяем, что текущий пользователь не кандидат
        if (currentUser.getRole().getRole_name().equals("candidate")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Кандидаты не могут голосовать.");
        }

        // Создаем запись о голосовании
        StudentVoice studentVoice = new StudentVoice();
        studentVoice.setStudent(currentUser);

        // Получаем пользователя-кандидата по его идентификатору
        User candidate = userService.getUserById(candidateId);
        if (candidate == null) {
            return ResponseEntity.notFound().build();
        }
        studentVoice.setCandidate(candidate);

        // Сохраняем запись о голосовании
        studentVoiceService.saveStudentVoice(studentVoice);

        return ResponseEntity.ok("Голос за кандидата успешно засчитан.");
    }



    @GetMapping("/currentUserExists")
    public ResponseEntity<Map<String, Boolean>> checkIfCurrentUserExists() {
        // Получаем текущего пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Проверяем, существует ли текущий пользователь в базе данных
        boolean exists = studentVoiceService.checkIfStudentExists(currentUser.getId());

        // Формируем объект JSON с ключом "isExist"
        Map<String, Boolean> response = new HashMap<>();
        response.put("isExist", exists);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticDto> getStatistic() {
        StatisticDto statisticDto = statisticService.getStatistic();
        return ResponseEntity.ok().body(statisticDto);
    }

}
