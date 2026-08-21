package com.taskmanagement.backend.dto;

import com.taskmanagement.backend.entity.Board;

import java.time.Instant;
import java.util.UUID;

public record BoardResponse(UUID id, String title, Instant createdAt) {

    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getId(), board.getTitle(), board.getCreatedAt());
    }
}
