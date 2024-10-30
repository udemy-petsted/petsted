package com.udemy.petsted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetstedApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetstedApplication.class, args);
    }

}
