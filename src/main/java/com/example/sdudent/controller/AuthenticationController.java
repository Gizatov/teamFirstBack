package com.example.sdudent.controller;

import com.example.sdudent.dto.AuthenticationRequest;
import com.example.sdudent.dto.AuthenticationResponse;
import com.example.sdudent.dto.RegisterRequest;
import com.example.sdudent.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(authenticationService.getAll());
    }

}


