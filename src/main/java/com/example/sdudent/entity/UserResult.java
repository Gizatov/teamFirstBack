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
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int userCount;
}

