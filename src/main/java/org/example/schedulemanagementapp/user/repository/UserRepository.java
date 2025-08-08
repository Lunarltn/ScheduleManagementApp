package org.example.schedulemanagementapp.user.repository;

import org.example.schedulemanagementapp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
