package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.dto.UserDto;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.mapper.UserMapper;
import com.sidibrahim.Aman.repository.UserRepository;
import com.sidibrahim.Aman.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    @Transactional
    @PreAuthorize("!hasAuthority('AGENT')")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }
}
