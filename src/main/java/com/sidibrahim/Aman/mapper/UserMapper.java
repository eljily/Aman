package com.sidibrahim.Aman.mapper;

import com.sidibrahim.Aman.dto.UserDto;
import com.sidibrahim.Aman.entity.Agency;
import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.exception.GenericException;
import com.sidibrahim.Aman.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
@RequiredArgsConstructor
public class UserMapper {
    private final AgencyRepository agencyRepository;
    public User toUser(UserDto userDto){
        Agency agency =userDto.getAgencyId()!=null?agencyRepository.findById(userDto.getAgencyId()).orElseThrow(()->
                new GenericException("Agency not found")):null;
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .agency(agency)
                .role(userDto.getRole())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .build();
    }
    public UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .agencyId(user.getAgency()!=null?user.getAgency().getId():null)
                .build();
    }
}
