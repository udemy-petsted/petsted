package com.udemy.petsted.comment.entity;

import com.udemy.petsted.comment.dto.request.CommentCreateRequestDto;
import com.udemy.petsted.comment.dto.request.CommentUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "comment")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(insertable = false)
    private LocalDate updatedAt;

//    @ManyToOne
//    @JoinColumn(name = "board_id", nullable = false)
//    private BoardEntity board;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity user;

    public static CommentEntity of(CommentCreateRequestDto createRequestDto) {
        return CommentEntity.builder()
            .content(createRequestDto.getContent())
            .createdAt(LocalDate.now())
            .build();
    }

    public void modify(CommentUpdateRequestDto updateRequestDto) {
        this.content = updateRequestDto.getContent();
        this.updatedAt = LocalDate.now();
    }
}
