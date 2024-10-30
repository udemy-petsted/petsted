package com.udemy.petsted.boards;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pet_qna")
public class PetQna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pet_qna_id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Boards board;

    @Column(length = 20)
    private String title;
}
