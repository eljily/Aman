package com.sidibrahim.Aman.dto;

import com.sidibrahim.Aman.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Role role;
    private Long agencyId;
}
