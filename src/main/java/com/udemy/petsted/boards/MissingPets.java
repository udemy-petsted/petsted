package com.udemy.petsted.boards;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "MissingPets")
public class MissingPets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer missing_pets_id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Boards board;

    @Column(length = 50)
    private String region;

    private LocalDateTime from;
    private LocalDateTime to;
}
