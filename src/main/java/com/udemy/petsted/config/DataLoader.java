package com.udemy.petsted.config;

import com.udemy.petsted.auth.entity.User;
import com.udemy.petsted.auth.repository.UserRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 100; i++) {
                User user = User.builder()
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
                    .build();
                Double j = Double.longBitsToDouble(
                    Math.round(faker.random().nextDouble(0.0, 99.9)));
                userRepository.save(user);
            }
        };
    }
}
