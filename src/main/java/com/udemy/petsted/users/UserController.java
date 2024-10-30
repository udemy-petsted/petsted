package com.udemy.petsted.users;


import com.udemy.petsted.users.UserEntity.ApiResponse;
import com.udemy.petsted.users.dto.UserCreateRequestDto;
import com.udemy.petsted.users.dto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/create")
    public String signForm(){
        return "signup";
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<SiteUser>> createUser(@Valid @RequestBody UserCreateRequestDto requestDto){
        try {
            SiteUser newUser = userService.create(requestDto);

            ApiResponse<SiteUser> response = new ApiResponse<>(
                "success",
                "User created successfully",
                newUser
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateKeyException e) {
            ApiResponse<SiteUser> response = new ApiResponse<>(
                "error",
                "아이디 또는 이메일이 이미 존재합니다.",
                null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(
        @PathVariable Long user_id,
        @RequestHeader(value = "Authorization") String token,
        @AuthenticationPrincipal UserDetails currentUser) {
        try {
            SiteUser user = userService.findUserById(user_id);

            if(user == null) {
                ApiResponse<UserResponseDto> response = new ApiResponse<>(
                    "error",
                    "해당 사용자를 찾을 수 없습니다.",
                    null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            boolean isOwner = currentUser != null && currentUser.getUsername().equals(user.getUsername());
            UserResponseDto userResponseDto;

            if (isOwner) {
                userResponseDto = new UserResponseDto(user, true);
            }
            else {
                userResponseDto = new UserResponseDto(user, false);
            }

            ApiResponse<UserResponseDto> response = new ApiResponse<>(
                "success",
                "User retrieved successfully",
                userResponseDto
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ApiResponse<UserResponseDto> response = new ApiResponse<>(
                "error",
                "인증이 필요합니다. 유효한 자격 증명을 입력해 주세요.",
                null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}

