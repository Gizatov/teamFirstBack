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
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private int course;
    private String faculty;
    private String studentId;
    private Role role;

}

