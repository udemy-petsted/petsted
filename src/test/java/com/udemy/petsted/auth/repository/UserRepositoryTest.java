package com.udemy.petsted.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.udemy.petsted.auth.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
// 인메모리 데이터 베이스가 아닌 외부 데이터베이스 (mysql)을 사용할 때 필요한 어노테이션
// Replace.NONE은 자동으로 데이터베이스를 교체하지 않고
// application.yml같은 설정파일에 명시된 데이터베이스를 사용하게 함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        User user = User.builder()
            .username("test")
            .password("test")
            .email("test")
            .hasPet(false)
            .profileUrl("test")
            .phoneNumber("test")
            .birthDate("test")
            .region("test")
            .manner(0.0)
            .build();
        userRepository.save(user);
        System.out.println("user.getId() = " + user.getId());
        System.out.println(userRepository.findAll().get(0));
        assertThat(user.getId()).isNotNull();
    }
}