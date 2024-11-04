package com.udemy.petsted.config;

import com.udemy.petsted.auth.entity.User;

import java.util.List;
import java.util.stream.IntStream;

import net.datafaker.Faker;


public class DummyUserDataList {

    public static List<User> create(int count) {
        Faker faker = new Faker();

        return IntStream.range(0, count)
            .mapToObj(i ->
                User.builder()
                    .nickname("dummy" + i)
                    .username("dummy" + i)
                    .password(faker.internet().password())
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
