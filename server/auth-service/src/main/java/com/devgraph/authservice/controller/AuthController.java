package com.devgraph.authservice.controller;

import com.devgraph.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // This is a temporary DTO (Data Transfer Object) just to catch the username/password
    public static class AuthRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        
        // TODO: In a real app, we would check if the password is correct in the database here!
        // For now, we are just assuming they are correct to get the JWT generation working.
        
        // 1. Generate the VIP Pass (JWT)
        String token = jwtUtil.generateToken(request.username);
        
        // 2. Hand it back to the user
        return ResponseEntity.ok(token);
    }
}
