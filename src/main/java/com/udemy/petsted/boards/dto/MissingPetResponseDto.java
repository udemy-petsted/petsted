package com.udemy.petsted.boards.dto;

import com.udemy.petsted.boards.Entity.Boards;

import com.udemy.petsted.boards.Entity.MissingPets;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissingPetResponseDto {

    private Boards board;
    private String region;
    private LocalDateTime from;
    private LocalDateTime to;

    private BoardResponseDto boardResponseDto;

    public MissingPetResponseDto(MissingPets missingPets){
        this.board = missingPets.getBoard();
        this.region = missingPets.getRegion();
        this.from = missingPets.getFrom();
        this.to = missingPets.getTo();
        this.boardResponseDto = new BoardResponseDto(board);
    }
}
