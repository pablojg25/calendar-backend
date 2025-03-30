package com.pablo.calendar_backend.service;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.auth.service.AuthService;
import com.pablo.calendar_backend.dto.SubscriptionResponse;
import com.pablo.calendar_backend.dto.UserDTO;
import com.pablo.calendar_backend.dto.UserNotificationResponse;
import com.pablo.calendar_backend.entity.Subscription;
import com.pablo.calendar_backend.entity.User;
import com.pablo.calendar_backend.entity.UserNotification;
import com.pablo.calendar_backend.repository.SubscriptionRepository;
import com.pablo.calendar_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    public ResponseEntity<ApiRes<SubscriptionResponse>> subscribe(String email) {
        String username = authService.getAuthenticatedUsername();
        User subscriber = userRepository.findByUsername(username).orElse(null);
        User subscribedTo = userRepository.findByEmailAndVisibleIsTrue(email).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "User not found";
        SubscriptionResponse body = null;
        if (subscriber != null && subscribedTo != null) {
            status = HttpStatus.BAD_REQUEST;
            message = "Can't subscribe to yourself";
            if (!subscriber.equals(subscribedTo)) {
                Subscription subscription = new Subscription();
                subscription.setSubscriber(subscriber);
                subscription.setSubscribedTo(subscribedTo);
                body = new SubscriptionResponse(subscriptionRepository.save(subscription));
                status = HttpStatus.CREATED;
                message = "Subscription created";
            }
        }
        ApiRes<SubscriptionResponse> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<Void>> unsubscribe(String email) {
        String username = authService.getAuthenticatedUsername();
        User subscriber = userRepository.findByUsername(username).orElse(null);
        User subscribedTo = userRepository.findByEmailAndVisibleIsTrue(email).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "User not found";
        if (subscriber != null && subscribedTo != null) {
            status = HttpStatus.BAD_REQUEST;
            message = "Can't subscribe to yourself";
            if (!subscriber.equals(subscribedTo)) {
                subscriptionRepository.deleteBySubscriberAndSubscribedTo(subscriber,subscribedTo);
                status = HttpStatus.OK;
                message = "Subscription deleted";
            }
        }
        ApiRes<Void> response = new ApiRes<>(
                status.value(),
                message,
                null
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<List<UserDTO>>> getSubscriptions() {
        String username = authService.getAuthenticatedUsername();
        User user = userRepository.findByUsername(username).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "User not found";
        List<UserDTO> body = null;
        if (user != null) {
            List<Subscription> foundSubscriptions = subscriptionRepository.findBySubscriber(user);
            body = new ArrayList<>();
            for (Subscription subscription : foundSubscriptions) {
                body.add(new UserDTO(subscription.getSubscribedTo()));
            }
            status = HttpStatus.OK;
            message = "Subscriptions list";
        }
        ApiRes<List<UserDTO>> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<Boolean>> checkSubscription(String email) {
        String username = authService.getAuthenticatedUsername();
        User subscriber = userRepository.findByUsername(username).orElse(null);
        User subscribedTo = userRepository.findByEmailAndVisibleIsTrue(email).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "User not found";
        boolean body = false;
        if (subscriber != null && subscribedTo != null) {
            body = subscriptionRepository.existsBySubscriberAndSubscribedTo(subscriber,subscribedTo);
            status = HttpStatus.OK;
            message = "Subscription check";
        }
        ApiRes<Boolean> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }
}
