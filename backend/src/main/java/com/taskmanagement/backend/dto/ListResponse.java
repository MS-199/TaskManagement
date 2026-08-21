package com.taskmanagement.backend.dto;

import com.taskmanagement.backend.entity.TaskList;

import java.time.Instant;
import java.util.UUID;

public record ListResponse(UUID id, UUID boardId, String title, Integer order, Instant createdAt) {

    public static ListResponse from(TaskList list) {
        return new ListResponse(
                list.getId(),
                list.getBoard().getId(),
                list.getTitle(),
                list.getOrder(),
                list.getCreatedAt());
    }
}
