package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.BoardResponse;
import com.taskmanagement.backend.dto.ListResponse;
import com.taskmanagement.backend.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardResponse> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public BoardResponse getBoardById(@PathVariable UUID id) {
        return boardService.getBoardById(id);
    }

    @GetMapping("/{id}/lists")
    public List<ListResponse> getListsByBoardId(@PathVariable UUID id) {
        return boardService.getListsByBoardId(id);
    }
}
