package com.sidibrahim.Aman.controller;

import com.sidibrahim.Aman.dto.ResponseMessage;
import com.sidibrahim.Aman.dto.UserDto;
import com.sidibrahim.Aman.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<ResponseMessage> getAllUsers() {
        return ResponseEntity.ok(ResponseMessage
                .builder()
                .message("Users List Retrieved Successfully")
                .status(HttpStatus.OK.value())
                .data(userService.getAllUsers())
                .build());
    }

    @PostMapping
    @PreAuthorize("!hasAuthority('AGENT')")
    public ResponseEntity<ResponseMessage> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(ResponseMessage
                .builder()
                .message("User Saved Successfully")
                .status(HttpStatus.OK.value())
                .data(userService.addUser(userDto))
                .build());
    }
}
