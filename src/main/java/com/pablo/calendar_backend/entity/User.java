package com.pablo.calendar_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(
            unique = true,
            nullable = false
    )
    private String username;

    @NotBlank
    @Email
    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    @NotBlank
    @Column(
            nullable = false
    )
    private String password;

    @OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    private ArrayList<UserNotification> userNotifications;

    public User() {
    }

    public User(Long id, String username, String email, String password, ArrayList<UserNotification> userNotifications) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userNotifications = userNotifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<UserNotification> getUserNotifications() {
        return userNotifications;
    }

    public void setUserNotifications(ArrayList<UserNotification> userNotifications) {
        this.userNotifications = userNotifications;
    }


}
