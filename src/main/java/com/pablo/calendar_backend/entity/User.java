package com.pablo.calendar_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

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

    @OneToMany(mappedBy="user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserNotification> userNotifications = new ArrayList<>();

    public User() {
        this.userNotifications = new ArrayList<>();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userNotifications = new ArrayList<>();
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

    public List<UserNotification> getUserNotifications() {
        return userNotifications;
    }

    public void setUserNotifications(List<UserNotification> userNotifications) {
        this.userNotifications = userNotifications;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


}
