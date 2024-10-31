package com.udemy.petsted.users.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private boolean hasPet;

    private String profileUrl;

    @Column(unique = true)
    private String phoneNumber;

    private String birthDate;

    private String region;

    private Double manner;

    private Date created_at;
    private Date updated_at;

    // 팔로우 목록 - 사용자가 팔로우하는 사람들
    @OneToMany(mappedBy = "follower", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> follower;

    // 팔로워 목록 - 사용자를 팔로우하는 사람들
    @OneToMany(mappedBy = "followee", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> followee;

    public void addFollowing(Follow follow) {
        follower.add(follow);
        follow.setFollower(this);
    }

    public void removeFollowing(Follow follow) {
        followee.remove(follow);
        follow.setFollower(null);
    }
}

