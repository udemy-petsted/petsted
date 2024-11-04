package com.udemy.petsted.boards.dto;

import com.udemy.petsted.boards.Entity.Boards;
import com.udemy.petsted.boards.Entity.PetCare;
import java.time.LocalDate;

public class PetCareResponseDto {
    private Boards board;

    private boolean needCare;
    private Integer care_role;
    private LocalDate scheduled_date;

    private BoardResponseDto boardResponseDto;

    public PetCareResponseDto(PetCare petCare){
        this.board = petCare.getBoard();
        this.needCare = petCare.isNeedCare();
        this.care_role = petCare.getCare_role();
        this.scheduled_date = petCare.getScheduled_date();

        this.boardResponseDto = new BoardResponseDto(board);
    }
}
