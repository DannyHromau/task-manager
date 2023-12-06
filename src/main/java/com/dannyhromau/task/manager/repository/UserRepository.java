package com.dannyhromau.task.manager.repository;

import com.dannyhromau.task.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
