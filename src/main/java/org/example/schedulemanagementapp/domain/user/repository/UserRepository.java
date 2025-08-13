package org.example.schedulemanagementapp.domain.user.repository;

import org.example.schedulemanagementapp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 유저 레포지토리
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
