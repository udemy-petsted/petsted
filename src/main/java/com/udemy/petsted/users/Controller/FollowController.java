package com.udemy.petsted.users.Controller;


import com.udemy.petsted.users.Entity.ResponseEntity.ApiResponse;
import com.udemy.petsted.users.Entity.User;
import com.udemy.petsted.users.Service.FollowService;
import com.udemy.petsted.users.Service.UserService;
import com.udemy.petsted.users.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users/{nickname}")
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private UserService userService;

    @PostMapping("/{targetNickname}")
    public ResponseEntity<ApiResponse> followUser(
        @PathVariable String nickname,
        @PathVariable String targetNickname,
        @AuthenticationPrincipal UserDetails currentUser) {

        followService.followUser(nickname, targetNickname);

        try {
            User user = userService.findByNickname(nickname);

            if(user == null) {
                ApiResponse<UserResponseDto> response = new ApiResponse<>(
                    "error",
                    "해당 사용자를 찾을 수 없습니다.",
                    null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<UserResponseDto> response = new ApiResponse<>(
                "success",
                "유저 팔로우가 완료되었습니다.",
                null
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
