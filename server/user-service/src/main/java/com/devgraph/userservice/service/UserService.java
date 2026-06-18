package com.devgraph.userservice.service;

import com.devgraph.userservice.entity.User;
import com.devgraph.userservice.repository.UserRepository;
import com.devgraph.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }
       
      if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Error: Email is already in use!");
        }

       return userRepository.save(user);
    }
}
