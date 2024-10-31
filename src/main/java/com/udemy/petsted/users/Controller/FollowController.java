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
    public ResponseEntity<ApiResponse<UserResponseDto>> followUser(
        @PathVariable String nickname,
        @PathVariable String targetNickname,
        @AuthenticationPrincipal UserDetails currentUser) {

        if (currentUser == null || !currentUser.getUsername().equals(nickname)) {
            ApiResponse<UserResponseDto> response = new ApiResponse<>(
                "error",
                "수정 권한이 없습니다.",
                null
            );
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        try {
            User user = userService.findByNickname(nickname);
            User targetUser = userService.findByNickname(targetNickname);

            if(user == null || targetUser == null) {
                ApiResponse<UserResponseDto> response = new ApiResponse<>(
                    "error",
                    "해당 사용자를 찾을 수 없습니다.",
                    null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            boolean alreadyFollowing = followService.isAlreadyFollowing(user, targetUser);
            if (alreadyFollowing) {
                ApiResponse<UserResponseDto> response = new ApiResponse<>(
                    "error",
                    "이미 팔로우 중인 사용자입니다.",
                    null
                );
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Long followId = followService.followUser(user, targetUser);

            UserResponseDto userResponseDto = new UserResponseDto(user);
            ApiResponse<UserResponseDto> response = new ApiResponse<>(
                "success",
                "유저 팔로우가 완료되었습니다.",
                userResponseDto
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
