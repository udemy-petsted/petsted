package com.udemy.petsted.boards.dto;

import com.udemy.petsted.boards.Entity.Boards;
import com.udemy.petsted.boards.Entity.PetQna;
import jakarta.persistence.Column;

public class PetQnaResponseDto {
    private Boards board;
    private String title;

    private BoardResponseDto boardResponseDto;

    public PetQnaResponseDto(PetQna petQna){
        this.board = petQna.getBoard();
        this.title = petQna.getTitle();
        this.boardResponseDto = new BoardResponseDto(board);
    }
}
