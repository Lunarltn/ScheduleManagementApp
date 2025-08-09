package org.example.schedulemanagementapp.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulemanagementapp.common.entity.BaseEntity;

/**
 * 유저 엔티티
 *
 * <p>시용자 이름, 이메일, 비밀번호, 생성/수정 시간을 관리한다</p>
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
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
