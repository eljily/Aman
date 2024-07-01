package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

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
    /* Only SuperAdmin or AgencyOwner can create a user . */
    //Todo : please remove logic to the Service
    @PreAuthorize("!hasAuthority('AGENT')")
    public ResponseEntity<?> save(@RequestBody User user) {
        Optional<User> userByPhoneNumber = userRepository.findUserByPhoneNumber(user.getPhoneNumber());
        if (userByPhoneNumber.isPresent()) {
            throw new GenericException("User Already Exist With PhoneNumber : " + user.getPhoneNumber());
        }
        String userPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(userPassword));
        user.setCreateDate(LocalDateTime.now());
        return ResponseEntity.ok(userRepository.save(user));
    }
}
