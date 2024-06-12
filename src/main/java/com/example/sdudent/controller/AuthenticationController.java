package com.example.sdudent.controller;

import com.example.sdudent.dto.AuthenticationRequest;
import com.example.sdudent.dto.AuthenticationResponse;
import com.example.sdudent.dto.RegisterRequest;
import com.example.sdudent.exception.EmailAlreadyExistsException;
import com.example.sdudent.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    // Importing necessary classes and libraries

    // Controller class for handling authentication-related requests

    private final AuthenticationService authenticationService;

    // Handles registration requests
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Возвращаем статус 400 с пустым телом
        }
    }


    // Handles authentication requests
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request)); // Returns ResponseEntity with authentication response
    }

    // Handles getting all users (for testing purposes)
    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(authenticationService.getAll()); // Returns ResponseEntity with all users
    }

}


