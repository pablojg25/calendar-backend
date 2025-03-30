package com.pablo.calendar_backend.controller;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.dto.SubscriptionResponse;
import com.pablo.calendar_backend.dto.UserDTO;
import com.pablo.calendar_backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("")
    public ResponseEntity<ApiRes<List<UserDTO>>> getSubscriptions() {
        return subscriptionService.getSubscriptions();
    }

    @PostMapping("/{email}")
    public ResponseEntity<ApiRes<SubscriptionResponse>> subscribe(@PathVariable String email) {
        return subscriptionService.subscribe(email);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<ApiRes<Void>> unsubscribe(@PathVariable String email) {
        return subscriptionService.unsubscribe(email);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ApiRes<Boolean>> checkSubscription(@PathVariable String email) {
        return subscriptionService.checkSubscription(email);
    }

}
