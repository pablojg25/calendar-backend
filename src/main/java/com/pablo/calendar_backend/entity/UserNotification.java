package com.pablo.calendar_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(
            nullable=false
    )
    private String title;

    @NotBlank
    @Column(
            nullable=false
    )
    private String content;

    @NotBlank
    @Column(
            nullable=false
    )
    private NotificationType type;

    @NotNull
    @Column(
            nullable = false
    )
    private LocalDate date;

    public UserNotification() {
    }

    public UserNotification(String title, Long id, String content, NotificationType type, LocalDate date) {
        this.title = title;
        this.id = id;
        this.content = content;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
