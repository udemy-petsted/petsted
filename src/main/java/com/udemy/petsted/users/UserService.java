package com.udemy.petsted.users;

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

    public SiteUser create(UserCreateRequestDto requestDto){
        SiteUser newUser = new SiteUser();

        newUser.setNickname(requestDto.getNickname());
        newUser.setUsername(requestDto.getUsername());
        newUser.setPassword(requestDto.getPassword());
        newUser.setEmail(requestDto.getEmail());
        newUser.setHasPet(requestDto.isHasPet());
        newUser.setProfileUrl(requestDto.getProfileUrl());
        newUser.setPhoneNumber(requestDto.getPhoneNumber());
        newUser.setBirthDate(requestDto.getBirthDate());
        newUser.setRegion(newUser.getRegion());
        newUser.setManner(30.0);
        newUser.setCreated_at(new Date());
        newUser.setUpdated_at(new Date());

        // 유저 정보 저장
        return userRepository.save(newUser);
    }
    public SiteUser findUserById(long userId){
        Optional<SiteUser> user = userRepository.findById(userId);

        return user.orElse(null);
    }
}