package com.udemy.petsted.boards;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetQnaRepository extends JpaRepository<PetQna, Integer> {
    List<PetQna> findAll();
    PetQna findByBoardId(Integer board_id);
    List<PetQna> findByTitle(String title);

}
