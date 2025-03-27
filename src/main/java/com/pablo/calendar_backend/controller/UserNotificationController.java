package com.pablo.calendar_backend.controller;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.dto.UserNotificationRequest;
import com.pablo.calendar_backend.dto.UserNotificationResponse;
import com.pablo.calendar_backend.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class UserNotificationController {

    @Autowired
    private UserNotificationService service;

    @GetMapping("")
    public ResponseEntity<ApiRes<List<UserNotificationResponse>>> findUserNotifications() {
        return service.findUserNotifications();
    }

    @PostMapping("")
    public ResponseEntity<ApiRes<UserNotificationResponse>> createNotification(@RequestBody UserNotificationRequest request) {
        return service.createNotification(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRes<UserNotificationResponse>> findNotificationById(@PathVariable Long id) {
        return service.findNotificationById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiRes<UserNotificationResponse>> updateNotification(@PathVariable Long id, @RequestBody UserNotificationRequest request) {
        return service.updateNotification(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRes<Void>> deleteNotification(@PathVariable Long id) {
        return service.deleteNotification(id);
    }

}
