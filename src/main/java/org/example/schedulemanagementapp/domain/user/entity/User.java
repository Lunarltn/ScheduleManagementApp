package org.example.schedulemanagementapp.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulemanagementapp.global.entity.BaseEntity;

/**
 * 유저 엔티티
 *
 * <p>시용자 이름, 이메일, 비밀번호, 생성/수정 시간을 관리한다</p>
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Column(nullable = false, length = 4)
    private String username;
    @Column(unique = true, nullable = false, length = 30)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void update(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
