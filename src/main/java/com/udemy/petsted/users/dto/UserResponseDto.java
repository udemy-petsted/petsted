package com.udemy.petsted.users.dto;

import com.udemy.petsted.users.SiteUser;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long userId;
    private String nickname;
    private String username;
    private String email;
    private boolean hasPet;
    private String profileUrl;
    private String phoneNumber;
    private String birthDate;
    private String region;
    private Double manner;
    private Date createdAt;
    private Date updatedAt;

    public UserResponseDto(SiteUser user, boolean isOwner) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.hasPet = user.isHasPet();
        this.profileUrl = user.getProfileUrl();
        this.phoneNumber = user.getPhoneNumber();
        this.region = user.getRegion();
        this.manner = user.getManner();
        this.createdAt = user.getCreated_at();
        this.updatedAt = user.getUpdated_at();
        if (isOwner) {
            this.email = user.getEmail();
            this.phoneNumber = user.getPhoneNumber();
            this.birthDate = user.getBirthDate();
        }
    }
}
