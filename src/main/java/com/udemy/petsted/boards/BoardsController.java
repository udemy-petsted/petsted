package com.udemy.petsted.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/boards")
public class BoardsController {

    @Autowired
    private final BoardsService boardsService;

    public BoardsController(BoardsService boardsService) {
        this.boardsService = boardsService;
    }

    @GetMapping(value = "/create")
    public String boardsForm() {
        return "boardForm";
    }

    @GetMapping(value = "/{board_id}")

}
