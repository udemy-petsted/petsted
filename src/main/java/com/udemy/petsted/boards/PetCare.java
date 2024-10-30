package com.udemy.petsted.boards;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@Table(name = "PetCare")
public class PetCare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pet_care;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Boards board;

    private boolean needCare;
    private Integer care_role;
    private LocalDate scheduled_date;
}
