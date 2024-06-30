package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody User user){
        String userPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(userPassword));
        user.setCreateDate(LocalDateTime.now());
        return ResponseEntity.ok(userRepository.save(user));
    }
}
