package com.sidibrahim.Aman.service;

import com.sidibrahim.Aman.dto.UserDto;
import com.sidibrahim.Aman.dto.request.UserToAgencyDto;
import com.sidibrahim.Aman.entity.Agency;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.mapper.AgencyMapper;
import com.sidibrahim.Aman.mapper.UserMapper;
import com.sidibrahim.Aman.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AgencyService agencyService;
    private final AgencyMapper agencyMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AgencyService agencyService, AgencyMapper agencyMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.agencyService = agencyService;
        this.agencyMapper = agencyMapper;
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

    @Transactional
    public String addUserToAgency(UserToAgencyDto userToAgencyDto) {
        User user = userRepository.findById(userToAgencyDto.getUserId()).orElseThrow(()-> new GenericException("User Not Found"));
        Agency agency = agencyMapper.toAgency(agencyService.getById(userToAgencyDto.getAgencyId()));
        user.setAgency(agency);
        userRepository.save(user);
        return "User Added To agency Successfully";
    }
}
