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
public class AuthenticationResponse {

    private String token; // Token for authentication
    private String name; // Name of the user
    private String email; // Email of the user
    private String role; // Role of the user
}
