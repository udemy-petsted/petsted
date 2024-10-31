package com.udemy.petsted.users.Service;

import com.udemy.petsted.users.Entity.Follow;
import com.udemy.petsted.users.Entity.User;
import com.udemy.petsted.users.Repository.FollowRepository;
import com.udemy.petsted.users.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public Long followUser(User follower, User followee) {
        Follow follow = Follow.builder()
            .follower(follower)
            .followee(followee)
            .build();
        followRepository.save(follow);
        return follow.getFollowId();
    }

    @Transactional(readOnly = true)
    public boolean isAlreadyFollowing(User follower, User followee) {
        return followRepository.existsByFollowerAndFollowee(follower, followee);
    }
}
