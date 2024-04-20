package com.example.sdudent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "users_result")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the user result

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Reference to the user associated with the result

    private int userCount; // Count or numerical result associated with the user
}
