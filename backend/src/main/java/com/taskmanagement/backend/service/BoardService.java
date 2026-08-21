package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.BoardResponse;
import com.taskmanagement.backend.dto.ListResponse;
import com.taskmanagement.backend.repository.BoardRepository;
import com.taskmanagement.backend.repository.ListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final ListRepository listRepository;

    public BoardService(BoardRepository boardRepository, ListRepository listRepository) {
        this.boardRepository = boardRepository;
        this.listRepository = listRepository;
    }

    public List<BoardResponse> getAllBoards() {
        return boardRepository.findAll().stream().map(BoardResponse::from).toList();
    }

    public BoardResponse getBoardById(UUID id) {
        return boardRepository.findById(id)
                .map(BoardResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found: " + id));
    }

    public List<ListResponse> getListsByBoardId(UUID boardId) {
        if (!boardRepository.existsById(boardId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found: " + boardId);
        }
        return listRepository.findByBoardIdOrderByOrderAsc(boardId).stream().map(ListResponse::from).toList();
    }
}
