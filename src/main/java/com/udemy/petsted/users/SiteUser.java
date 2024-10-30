package com.udemy.petsted.users;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
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

    private double manner;

    private Date created_at;
    private Date updated_at;

    //임시
}
