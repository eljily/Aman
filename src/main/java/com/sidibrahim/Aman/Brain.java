package com.sidibrahim.Aman;

import com.sidibrahim.Aman.entity.User;
import com.sidibrahim.Aman.repository.UserRepository;
import com.sidibrahim.Aman.service.UserService;
import com.sidibrahim.Aman.util.AdminConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class Brain {

	public static void main(String[] args) {
		SpringApplication.run(Brain.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder,
										AdminConfig adminConfig){
		return args ->{

			Optional<User> user = userRepository.findUserByPhoneNumber(adminConfig.getSuperAdminPhoneNumber());
			if(user.isEmpty()){
				userRepository.save(User.builder()
								.name(adminConfig.getSuperAdminName())
								.phoneNumber(adminConfig.getSuperAdminPhoneNumber())
								.password(passwordEncoder.encode(adminConfig.getSuperAdminPassword()))
								.createDate(LocalDateTime.now())
								.role(adminConfig.getSuperAdminRole())
								.isEnabled(true)
						.build());
			}
		};
	}

}
