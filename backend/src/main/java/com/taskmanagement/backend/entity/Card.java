package com.taskmanagement.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private TaskList list;

    @Column(nullable = false, columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private LocalDate dueDate;

    @Column(name = "\"order\"", nullable = false)
    private Integer order;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    @ManyToMany
    @JoinTable(
            name = "card_label",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<Label> labels = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public TaskList getList() {
        return list;
    }

    public void setList(TaskList list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Set<Label> getLabels() {
        return labels;
    }
}
