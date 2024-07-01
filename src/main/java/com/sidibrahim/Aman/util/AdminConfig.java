package com.sidibrahim.Aman.util;

import com.sidibrahim.Aman.enums.Role;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AdminConfig {
    @Value("${super.admin.phoneNumber}")
    private String superAdminPhoneNumber;

    @Value("${super.admin.password}")
    private String superAdminPassword;

    @Value("${super.admin.name}")
    private String superAdminName;

    private final Role superAdminRole = Role.SUPER_ADMIN;
}
