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

    private String token;
    private String name;
    private String email;
    private String role;

}
