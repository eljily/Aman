package com.sidibrahim.Aman.service;

import com.sidibrahim.Aman.dto.UserDto;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.mapper.UserMapper;
import com.sidibrahim.Aman.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addUser(UserDto userDto) {
        Optional<User> user = userRepository.findUserByPhoneNumber(userDto.getPhoneNumber());
        if (user.isPresent()) {
            throw new GenericException("User Already Exists");
        }
        String userPassword = userDto.getPassword();
        userDto.setPassword(passwordEncoder.encode(userPassword));
        User userEntity = userMapper.toUser(userDto);
        return userMapper.toUserDto(userRepository.save(userEntity));
    }

    public List<UserDto> getAllUsers() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }
}
