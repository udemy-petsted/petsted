package com.udemy.petsted.config;

import com.udemy.petsted.auth.entity.User;

import java.util.List;
import java.util.stream.IntStream;

import net.datafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 스프링 컨텍스트를 사용하지 않고 JPA 기능만 사용하는 @DataJpaTest에서도 사용하기 위해
 * 컴포넌트로 지정하지 않고 메서드들을 static으로 지정했다
 */
public class DummyUserDataList {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static List<User> create(int count) {
        Faker faker = new Faker();

        return IntStream.range(0, count)
            .mapToObj(i ->
                User.builder()
                    .nickname("dummy" + i)
                    .username("dummy" + i)
                    .password(passwordEncoder.encode("dummy"))
                    .email(faker.internet().emailAddress())
                    .hasPet(false)
                    .profileUrl(faker.internet().url())
                    .phoneNumber(faker.phoneNumber().cellPhone())
                    .birthDate(faker.date().birthday(1, 99, "YYYY-MM-DD"))
                    .region(faker.address().country())
                    // 소수점 두 자리에서 반올림한다
                    .manner(Math.round(faker.random().nextDouble(0.0, 99.9) * 10) / 10.0)
                    .role("USER")
                    .build()
            ).toList();

    }
}
