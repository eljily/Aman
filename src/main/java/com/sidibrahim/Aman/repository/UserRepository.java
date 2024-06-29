package com.sidibrahim.Aman.repository;

import com.sidibrahim.Aman.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
