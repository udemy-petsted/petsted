package com.udemy.petsted.util;

import com.udemy.petsted.auth.entity.User;

public class TestUtils {
    /**
     * num으로 고유성을 가진다
     *
     * @param num 고유 번호
     * @return user
     */
    public static User createUser(int num) {
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
            .role("USER")
            .build();
    }
}
