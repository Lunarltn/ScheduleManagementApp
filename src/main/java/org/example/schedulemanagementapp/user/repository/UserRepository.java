package org.example.schedulemanagementapp.user.repository;

import org.example.schedulemanagementapp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 유저 레포지토리
public interface UserRepository extends JpaRepository<User, Long> {
}
