package com.example.sdudent.dto;

import com.example.sdudent.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name; // Name of the user
    private String lastName; // Last name of the user
    private String email; // Email of the user
    private String password; // Password of the user
    private String gender; // Gender of the user
    private String course; // Course of the user
    private String faculty; // Faculty of the user
    private String studentId; // Student ID of the user
    private Role role; // Role of the user
}
