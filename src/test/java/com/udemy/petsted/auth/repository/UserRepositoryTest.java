package com.udemy.petsted.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.udemy.petsted.auth.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
// 인메모리 데이터 베이스가 아닌 외부 데이터베이스 (mysql)을 사용할 때 필요한 어노테이션
// Replace.NONE은 자동으로 데이터베이스를 교체하지 않고
// application.yml같은 설정파일에 명시된 데이터베이스를 사용하게 함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// NOT_SUPPORTED 설정은 트랜잭션을 사용하지 않겠다는 의미
// 현재는 트랜잭션을 사용해야 테스트가 동작하기 때문에 주석처리 했다
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class UserRepositoryTest {

    // user.sql 때문에 데이터가 실행시 먼저 모두 1씩 증가해있다
    private final int SIZE = 101;
    private final Long USER_ID = 2L;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        User user = User.builder()
            .nickname("test")
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

    @Test
    public void testInsertDummies() {

        for (int i = 0; i < 100; i++) {
            User user = User.builder()
                .nickname("test" + i)
                .username("test" + i)
                .password("test" + i)
                .email("test" + i)
                .hasPet(false)
                .profileUrl("test" + i)
                .phoneNumber("test" + i)
                .birthDate("test" + i)
                .region("test" + i)
                .manner(0.0)
                .build();
            userRepository.save(user);
        }

        assertThat(userRepository.findAll().size()).isEqualTo(SIZE);
    }

    @Test
    public void testRead() {
        testInsert();
        Optional<User> result = userRepository.findById(USER_ID);
        assertThat(result.isPresent()).isTrue();
    }
}