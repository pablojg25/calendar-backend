package com.pablo.calendar_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pablo.calendar_backend.entity.UserNotification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UserNotificationResponse {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank
    private String type;

    private Boolean expired;

    public UserNotificationResponse() {
    }

    public UserNotificationResponse(UserNotification userNotification) {
        this.title = userNotification.getTitle();
        this.content = userNotification.getContent();
        this.date = userNotification.getDate();
        this.type = userNotification.getType().getName();
        this.expired = this.date.isBefore(LocalDate.now());
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getExpired() {return this.expired;}

}
