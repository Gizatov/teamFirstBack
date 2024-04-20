package com.example.sdudent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students_voice")
public class StudentVoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the student voice

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User student; // Reference to the student who casts the vote

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private User candidate; // Reference to the candidate receiving the vote
}
