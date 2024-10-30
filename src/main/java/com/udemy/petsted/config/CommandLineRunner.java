package com.udemy.petsted.config;


import com.udemy.petsted.users.UserService;
import com.udemy.petsted.users.dto.UserCreateRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final UserService userService;

    public CommandLineRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 유저 1 추가
        UserCreateRequestDto user1 = new UserCreateRequestDto();
        user1.setNickname("petlover99");
        user1.setUsername("petlover@example.com");
        user1.setPassword("securePassword123");
        user1.setEmail("petlover@example.com");
        user1.setHasPet(true);
        user1.setProfileUrl("https://example.com/images/profile123.jpg");
        user1.setPhoneNumber("+8201012345678");
        user1.setBirthDate("1995-06-15");
        user1.setRegion("Seoul");
        user1.setManner(4.5);
        userService.create(user1);

        // 유저 2 추가
        UserCreateRequestDto user2 = new UserCreateRequestDto();
        user2.setNickname("catfan");
        user2.setUsername("catfan@example.com");
        user2.setPassword("securePassword456");
        user2.setEmail("catfan@example.com");
        user2.setHasPet(false);
        user2.setProfileUrl("https://example.com/images/profile124.jpg");
        user2.setPhoneNumber("+8201098765432");
        user2.setBirthDate("1990-08-20");
        user2.setRegion("Busan");
        user2.setManner(4.8);
        userService.create(user2);

        // 임시
    }
}

