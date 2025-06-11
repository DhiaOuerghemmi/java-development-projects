package com.example.phr.auth.controller;

import com.example.phr.auth.model.User;
import com.example.phr.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestParam String username,
                                       @RequestParam String password) {
        var created = userService.register(username, password);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {
        // TODO: authenticate, generate JWT
        return ResponseEntity.ok("token-placeholder");
    }
}
