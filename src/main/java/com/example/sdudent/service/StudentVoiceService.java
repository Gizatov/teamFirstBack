package com.example.sdudent.service;

import com.example.sdudent.entity.StudentVoice;
import com.example.sdudent.repository.StudentVoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentVoiceService {

    private final StudentVoiceRepository studentVoiceRepository;

    // Constructor injection of StudentVoiceRepository dependency
    public StudentVoiceService(StudentVoiceRepository studentVoiceRepository) {
        this.studentVoiceRepository = studentVoiceRepository;
    }

    // Method to save student voice data
    public void saveStudentVoice(StudentVoice studentVoice) {
        studentVoiceRepository.save(studentVoice);
    }

    // Method to check if a student exists by their ID
    public boolean checkIfStudentExists(Long studentId) {
        return studentVoiceRepository.existsByStudentId(studentId);
    }

    // Method to retrieve the candidate ID for a given student ID
    public Long getCandidate(Long studentId) {
        return studentVoiceRepository.findCandidateIdByStudentId(studentId);
    }
}
