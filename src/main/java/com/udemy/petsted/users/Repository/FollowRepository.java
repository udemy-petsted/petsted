package com.udemy.petsted.users.Repository;

import com.udemy.petsted.users.Entity.Follow;
import com.udemy.petsted.users.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowee(User follower, User followee);
}
