package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.dto.UserDto;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.mapper.UserMapper;
import com.sidibrahim.Aman.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping
    @Transactional
    /* Only SuperAdmin or AgencyOwner can create a user . */
    //Todo : please remove logic to the Service
    @PreAuthorize("!hasAuthority('AGENT')")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        Optional<User> userByPhoneNumber = userRepository.findUserByPhoneNumber(userDto.getPhoneNumber());
        if (userByPhoneNumber.isPresent()) {
            throw new GenericException("User Already Exist With PhoneNumber : " + userDto.getPhoneNumber());
        }
        String userPassword = userDto.getPassword();
        userDto.setPassword(passwordEncoder.encode(userPassword));
        User user = userMapper.toUser(userDto);
        return ResponseEntity.ok(userRepository.save(user));
    }
}
