package com.udemy.petsted.users.Service;

import com.udemy.petsted.users.Entity.User;
import com.udemy.petsted.users.Repository.UserRepository;
import com.udemy.petsted.users.dto.UserCreateRequestDto;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateRequestDto userCreateRequestDto){
        User newUser = User.builder()
            .nickname(userCreateRequestDto.getNickname())
            .username(userCreateRequestDto.getUsername())
            .password(userCreateRequestDto.getPassword()) // 실제로는 암호화를 해야 함
            .email(userCreateRequestDto.getEmail())
            .hasPet(userCreateRequestDto.isHasPet())
            .profileUrl(userCreateRequestDto.getProfileUrl())
            .phoneNumber(userCreateRequestDto.getPhoneNumber())
            .birthDate(userCreateRequestDto.getBirthDate())
            .region(userCreateRequestDto.getRegion())
            .manner(30.0)
            .created_at(new Date())
            .updated_at(new Date())
            .build();

        // 유저 정보 저장
        return userRepository.save(newUser);
    }
    public User findByNickname(String nickname){
        Optional<User> user = userRepository.findByNickname(nickname);

        return user.orElse(null);
    }

    public User findByUserId(Long userId){
        Optional<User> user = userRepository.findByUserId(userId);

        return user.orElse(null);
    }
}