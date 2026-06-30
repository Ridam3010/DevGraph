package com.devgraph.userservice.service;
import com.devgraph.userservice.entity.Profile;
import com.devgraph.userservice.entity.User;
import com.devgraph.userservice.repository.ProfileRepository;
import com.devgraph.userservice.repository.UserRepository;
import com.devgraph.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    public User registerUser(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Error:Usename is already taken");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Error:Email is already in use");
        }
        Profile profile=new Profile();
        profile.setUser(user);
        user.setProfile(profile);
        return userRepository.save(user);
    }
    public boolean loginUser(String username,String password){
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Error: Profile not found for user ID: " + userId));
    }
    public Profile updateProfile(Long userId, Profile updatedProfile) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Error: Profile not found for user ID: " + userId));
        
        profile.setBio(updatedProfile.getBio());
        profile.setGithubUrl(updatedProfile.getGithubUrl());
        
        return profileRepository.save(profile);
    }
}