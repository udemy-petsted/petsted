package com.udemy.petsted.boards;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "board")
public class Boards {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer board_id;

    @ManyToOne
    private SiteUser Siteuser;

    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String hashtag;

    @CreatedDate
    private LocalDate created_at;
    @UpdateTimestamp
    private LocalDate updated_at;

    private Integer post_type;

    private Integer like_count;

    @ManyToMany
    Set<SiteUser> voter;


}
