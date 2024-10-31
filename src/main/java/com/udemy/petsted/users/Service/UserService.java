package com.udemy.petsted.users.Service;

import com.udemy.petsted.users.Entity.User;
import com.udemy.petsted.users.Repository.UserRepository;
import com.udemy.petsted.users.dto.UserCreateRequestDto;
import com.udemy.petsted.users.dto.UserUpdateRequestDto;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User updateUserByNickname(String nickname, UserUpdateRequestDto requestDto) {
        Optional<User> optionalUser = userRepository.findByNickname(nickname);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("해당 사용자를 찾을 수 없습니다");
        }

        User user = optionalUser.get();

        User updatedUser = user.toBuilder()
            .nickname(requestDto.getNickname() != null ? requestDto.getNickname() : user.getNickname())
            .email(requestDto.getEmail() != null ? requestDto.getEmail() : user.getEmail())
            .phoneNumber(requestDto.getPhoneNumber() != null ? requestDto.getPhoneNumber() : user.getPhoneNumber())
            .profileUrl(requestDto.getProfileUrl() != null ? requestDto.getProfileUrl() : user.getProfileUrl())
            .birthDate(requestDto.getBirthDate() != null ? requestDto.getBirthDate() : user.getBirthDate())
            .region(requestDto.getRegion() != null ? requestDto.getRegion() : user.getRegion())
            .hasPet(requestDto.getHasPet() != null ? requestDto.getHasPet() : user.isHasPet())
            .build();

        return userRepository.save(updatedUser);
    }

    @Transactional
    public User deleteUserByNickname(String nickname){
        Optional<User> optionalUser = userRepository.findByNickname(nickname);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("해당 사용자를 찾을 수 없습니다");
        }

        User user = optionalUser.get();
        userRepository.delete(user);

        return user;
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