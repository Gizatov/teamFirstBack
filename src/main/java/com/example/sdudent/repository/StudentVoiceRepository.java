package com.example.sdudent.repository;

import com.example.sdudent.entity.StudentVoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentVoiceRepository extends JpaRepository<StudentVoice, Long> {

    // Method to check if a student voice exists by student ID
    boolean existsByStudentId(Long studentId);

    // Custom query method to find the candidate ID by student ID
    @Query("SELECT sv.candidate.id FROM StudentVoice sv WHERE sv.student.id = :studentId")
    Long findCandidateIdByStudentId(@Param("studentId") Long studentId);

    // Custom native query to count votes for each candidate
    @Query(value = "SELECT candidate_id, COUNT(*) AS votes_count " +
            "FROM students_voice " +
            "GROUP BY candidate_id " +
            "ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<Object[]> countVotesByCandidateId();
}
