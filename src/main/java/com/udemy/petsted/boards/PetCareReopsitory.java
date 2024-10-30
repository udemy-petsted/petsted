package com.udemy.petsted.boards;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetCareReopsitory extends JpaRepository<PetCare, Integer> {
    List<PetCare> findAll();
    PetCare findByBoardId(Integer board_id);
    List<PetCare> findByNeedCare(boolean needCare);
    List<PetCare> findByCareRole(Integer careRole);



}
