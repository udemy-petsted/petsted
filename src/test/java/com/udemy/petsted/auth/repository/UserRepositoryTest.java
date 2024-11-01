package com.udemy.petsted.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.udemy.petsted.auth.entity.User;
import com.udemy.petsted.config.DummyUserDataList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@DataJpaTest
// 인메모리 데이터 베이스가 아닌 외부 데이터베이스 (mysql)을 사용할 때 필요한 어노테이션
// Replace.NONE은 자동으로 데이터베이스를 교체하지 않고
// application.yml같은 설정파일에 명시된 데이터베이스를 사용하게 함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// NOT_SUPPORTED 설정은 트랜잭션을 사용하지 않겠다는 의미
// 현재는 트랜잭션을 사용해야 테스트가 동작하기 때문에 주석처리 했다
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("사용자를 저장하고 저장된 사용자를 조회하여 동일한지 확인한다.")
    public void testInsertAndFind() {
        int num = 1;
        User user = createUser(num);
        User notSavedUser = createUser(num + 1);
        userRepository.save(user);
        User savedUser = userRepository.findById(user.getId()).orElse(null);

        // usingRecursiveComparison() : 객체 전체의 모든 필드를 재귀적으로 비교한다.
        assertThat(user).usingRecursiveComparison().isEqualTo(savedUser);
        assertThat(user).usingRecursiveComparison().isNotEqualTo(notSavedUser);
    }

    @Test
    @DisplayName("여러 사용자를 저장하고 동일한 숫자의 사용자가 저장되었는지 확인한다.")
    public void testInsertDummies() {
        // 자동으로 실행되는 SQL 문 때문에 생기는 데이터를 제거한다.
        userRepository.deleteAll();

        int size = 100;
        for (int i = 0; i < size; i++) {
            User user = createUser(i);
            userRepository.save(user);
        }
        assertThat(userRepository.findAll().size()).isEqualTo(size);
    }

    @Test
    @DisplayName("영속성 컨테이너 내부에 들어있는 객체 업데이트 시 자동으로 DB에"
        + "반영되는지 확인한다.")
    public void testUpdateDirtyCheck() {
        int num = 1;
        String newNickname = "newNickname";

        User user = createUser(num);
        userRepository.save(user);

        user.changeNickname(newNickname);
        User savedUser = userRepository.findById(user.getId()).orElse(null);

        assertThat(savedUser)
            .extracting(User::getNickname)
            .isEqualTo(newNickname);
    }

    @Test
    @DisplayName("사용자가 정상적으로 제거되었는지 확인한다.")
    public void testDelete() {
        int num = 1;
        User user = createUser(num);
        Long savedId = userRepository.save(user).getId();

        userRepository.delete(user);
        assertThat(userRepository.findById(savedId)).isEmpty();
    }

    @Test
    @DisplayName("페이징 처리가 정상적으로 작동하는지 확인한다.")
    public void testPaging() {
        // Given: 더미데이터 생성
        userRepository.saveAll(DummyUserDataList.create(100));

        // When: 페이징 요청 설정
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id")
            .descending());
        Page<User> result = userRepository.findAll(pageable);

        // Then: 페이징 결과 검증
        //  페이지 사이즈가 10인지 확인
        assertThat(result.getSize()).isEqualTo(10);
        // 요청한 페이지 번호가 0인지 확인
        assertThat(result.getNumber()).isEqualTo(0);
        // 전체 페이지 수가 예상대로 10인지 확인 ( 100개 / 페이지당 10개)
        assertThat(result.getTotalPages()).isEqualTo(10);
        // 전체 항목 수가 100개인지 확인
        assertThat(result.getTotalElements()).isEqualTo(100);

        // 첫번째 페이지의 첫번째 항목의 id가 내림차순 정렬에 맞게 두 번째 항목의 id보다 큰지 확인
        assertThat(result.getContent().get(0).getId())
            .isGreaterThan(result.getContent().get(1).getId());

    }

    /**
     * num으로 고유성을 가진다
     *
     * @param num 고유 번호
     * @return user
     */
    private User createUser(int num) {
        return User.builder()
            .nickname("test" + num)
            .username("test" + num)
            .password("test")
            .email("test" + num)
            .hasPet(false)
            .profileUrl("test")
            .phoneNumber("test" + num)
            .birthDate("test")
            .region("test")
            .manner(0.0)
            .build();
    }
}