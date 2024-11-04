//package com.udemy.petsted.auth.repository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.udemy.petsted.auth.entity.User;
//import com.udemy.petsted.auth.exception.UserExceptions;
//import java.util.Optional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@SpringBootTest
//class BootUserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    @DisplayName("passwordEncoder를 이용한 사용자가 저장되는지 확인한다.")
//    public void testInsert() {
//        createUser(10);
//    }
//
//
//    @Test
//    public void testRead() {
//
//
//        Optional<User> result = userRepository.findById(id);
//
//        User user = result.orElseThrow(UserExceptions.NOT_FOUND::get);
//
//    }
//
//
//}