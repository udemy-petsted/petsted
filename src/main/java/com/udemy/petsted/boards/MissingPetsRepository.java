package com.udemy.petsted.boards;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPetsRepository extends JpaRepository<MissingPets, Integer> {
    List<MissingPets> findAll();
    MissingPets findByBoardId(Integer board_id);
}
