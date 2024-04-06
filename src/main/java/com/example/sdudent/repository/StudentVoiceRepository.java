package com.example.sdudent.repository;

import com.example.sdudent.entity.StudentVoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentVoiceRepository extends JpaRepository<StudentVoice, Long> {

    boolean existsByStudentId(Long studentId);
//    @Query("SELECT sv.candidate FROM StudentVoice sv WHERE sv.student = ?1")
//    Long getCandidateIdByStudentId(Long studentId);

    @Query("SELECT sv.candidate.id FROM StudentVoice sv WHERE sv.student.id = :studentId")
    Long findCandidateIdByStudentId(@Param("studentId") Long studentId);

    @Query(value = "SELECT candidate_id, COUNT(*) AS votes_count " +
            "FROM students_voice " +
            "GROUP BY candidate_id " +
            "ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<Object[]> countVotesByCandidateId();
}
