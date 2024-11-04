package com.udemy.petsted.boards.dto;

import com.udemy.petsted.boards.Entity.Boards;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private SiteUser siteUser;
    private String content;
    private String hashtag;
    private Integer post_type;

    private LocalDate created_at;
    private LocalDate updated_at;

    public BoardResponseDto(Boards board){
        this.siteUser = board.getSiteUser();
        this.content = board.getContent();
        this.hashtag = board.getHashtag();
        this.post_type = board.getPost_type();
        this.created_at = board.getCreated_at();
        this. updated_at = board.getUpdated_at();
    }
}
