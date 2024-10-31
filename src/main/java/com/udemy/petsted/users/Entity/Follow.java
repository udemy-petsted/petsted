package com.udemy.petsted.users.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
@Table(name = "follows", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"follower", "followee"})
}, indexes = {
    @Index(name = "idx_follower", columnList = "follower"),
    @Index(name = "idx_followee", columnList = "followee")
})
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne
    @JoinColumn(name = "follower", referencedColumnName = "userId", nullable = false)
    @JsonIgnore
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followee", referencedColumnName = "userId", nullable = false)
    @JsonIgnore
    private User followee;
}
