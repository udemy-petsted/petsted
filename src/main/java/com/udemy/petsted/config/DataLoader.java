package com.udemy.petsted.config;

import com.udemy.petsted.auth.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
        DummyUserDataList dummyUserDataList) {
        return args -> {
            userRepository.saveAll(dummyUserDataList.create(100));
        };
    }
}
