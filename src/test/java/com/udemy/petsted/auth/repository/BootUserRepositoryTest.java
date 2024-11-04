package com.udemy.petsted.auth.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.udemy.petsted.auth.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BootUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("passwordEncoder를 이용한 사용자가 저장되었는지 확인한다.")
    public void testInsert() {

        for (int i = 0; i < 10; i++) {

            User user = User.builder()
                .nickname("test" + i)
                .username("test" + i)
                .password(passwordEncoder.encode("1234"))
                .email("test" + i)
                .hasPet(false)
                .profileUrl("test")
                .phoneNumber("test" + i)
                .birthDate("test")
                .region("test")
                .manner(0.0)
                .role(i <= 8 ? "USER" : "ADMIN")
                .build();

            userRepository.save(user);
        }

    }
}