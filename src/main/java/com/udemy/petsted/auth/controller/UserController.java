package com.udemy.petsted.auth.controller;

import com.udemy.petsted.auth.entity.User;
import com.udemy.petsted.auth.service.UserService;
import com.udemy.petsted.dto.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
//        ApiResponse<List<User>> response = ApiResponse.<List<User>>builder()
//            .status("success")
//            .message("Users found")
//            .data(users)
//            .build();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public void save(User user) {
        userService.save(user);
    }


}
