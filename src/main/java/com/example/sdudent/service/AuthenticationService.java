package com.example.sdudent.service;

import com.example.sdudent.config.JwtService;
import com.example.sdudent.dto.AuthenticationRequest;
import com.example.sdudent.dto.AuthenticationResponse;
import com.example.sdudent.dto.RegisterRequest;
import com.example.sdudent.entity.Role;
import com.example.sdudent.entity.User;
import com.example.sdudent.repository.RoleRepository;
import com.example.sdudent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Method to handle user registration
    public AuthenticationResponse register(RegisterRequest request) {
        // Create a user object based on the request
        var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())  // Assuming you have a role field in RegisterRequest
                .gender(request.getGender()) // Handle gender field
                .course(Integer.parseInt(request.getCourse())) // Handle course field
                .faculty(request.getFaculty()) // Handle faculty field
                .student_id(request.getStudentId()) // Handle studentId field
                .build();

        // Save the user in the repository
        repository.save(user);

        // Create a JWT token for the new user
        var jwtToken = jwtService.generateToken(user);

        // Return the response with the token
        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().getRole_name())
                .token(jwtToken)
                .build();
    }

    // Method to handle user authentication
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            // Check if a user with the specified email exists
            var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Check if the entered password matches the user's hashed password
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }

            // If all checks pass, generate a token
            var jwtToken = jwtService.generateToken(user);

            // Return a successful response with the JWT token
            return AuthenticationResponse.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole().getRole_name())
                    .token(jwtToken)
                    .build();
        } catch (AuthenticationException e) {
            // Handle authentication errors
            throw new BadCredentialsException("Invalid email or password", e);
        }
    }

    // Method to get all users
    public List<User> getAll(){
        return repository.findAll();
    }
}
