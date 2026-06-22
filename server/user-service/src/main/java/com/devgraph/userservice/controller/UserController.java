package com.devgraph.userservice.controller;

import com.devgraph.userservice.entity.Profile;
import com.devgraph.userservice.entity.User;
import com.devgraph.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        boolean success = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<Profile> getUserProfile(@PathVariable Long id) {
        Profile profile = userService.getProfileByUserId(id);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<Profile> updateUserProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Profile updatedProfile = userService.updateProfile(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }
}
