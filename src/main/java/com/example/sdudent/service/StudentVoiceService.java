package com.example.sdudent.service;

import com.example.sdudent.entity.StudentVoice;
import com.example.sdudent.repository.StudentVoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentVoiceService  {

    private final StudentVoiceRepository studentVoiceRepository;

    public StudentVoiceService(StudentVoiceRepository studentVoiceRepository) {
        this.studentVoiceRepository = studentVoiceRepository;
    }


    public void saveStudentVoice(StudentVoice studentVoice) {
        studentVoiceRepository.save(studentVoice);
    }
    public boolean checkIfStudentExists(Long studentId) {
        return studentVoiceRepository.existsByStudentId(studentId);
    }

    public Long getCandidate (Long studentId){
        return studentVoiceRepository.findCandidateIdByStudentId(studentId);
    }


}
