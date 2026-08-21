package com.taskmanagement.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "list")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false, columnDefinition = "text")
    private String title;

    @Column(name = "\"order\"", nullable = false)
    private Integer order;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public UUID getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
