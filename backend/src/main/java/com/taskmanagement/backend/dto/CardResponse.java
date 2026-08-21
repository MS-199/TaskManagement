package com.taskmanagement.backend.dto;

import com.taskmanagement.backend.entity.Card;
import com.taskmanagement.backend.entity.Label;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CardResponse(
        UUID id,
        UUID listId,
        String title,
        String description,
        LocalDate dueDate,
        Integer order,
        Instant createdAt,
        Instant updatedAt,
        List<LabelResponse> labels) {

    public static CardResponse from(Card card) {
        return new CardResponse(
                card.getId(),
                card.getList().getId(),
                card.getTitle(),
                card.getDescription(),
                card.getDueDate(),
                card.getOrder(),
                card.getCreatedAt(),
                card.getUpdatedAt(),
                card.getLabels().stream().map(LabelResponse::from).toList());
    }

    public record LabelResponse(UUID id, String name, String color) {
        public static LabelResponse from(Label label) {
            return new LabelResponse(label.getId(), label.getName(), label.getColor());
        }
    }
}
