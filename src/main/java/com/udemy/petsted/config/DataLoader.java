package com.udemy.petsted.config;

import com.udemy.petsted.auth.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository
        ) {
        return args -> {
            userRepository.saveAll(DummyUserDataList.create(100));
        };
    }
}
