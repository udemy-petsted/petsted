package com.udemy.petsted.users.Service;

import com.udemy.petsted.users.Entity.Follow;
import com.udemy.petsted.users.Repository.FollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Follow followUser(String nickname, String targetNickname) {
        return null;
    }
}
