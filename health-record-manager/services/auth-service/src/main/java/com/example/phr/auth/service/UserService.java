package com.example.phr.auth.service;

import com.example.phr.auth.model.User;
import com.example.phr.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public User register(String username, String rawPassword) {
        var user = User.builder()
                       .username(username)
                       .password(encoder.encode(rawPassword))
                       .role("ROLE_USER")
                       .build();
        return userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username)
                       .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
