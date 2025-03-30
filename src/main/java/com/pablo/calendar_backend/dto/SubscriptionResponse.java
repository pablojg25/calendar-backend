package com.pablo.calendar_backend.dto;

import com.pablo.calendar_backend.entity.Subscription;
import jakarta.validation.constraints.NotNull;

public class SubscriptionResponse {

    @NotNull
    private Long id;

    @NotNull
    private UserDTO subscriber;

    @NotNull
    private UserDTO subscribedTo;

    public SubscriptionResponse() {
    }

    public SubscriptionResponse(Subscription subscription) {
        this.id = subscription.getId();
        this.subscriber = new UserDTO(subscription.getSubscriber());
        this.subscribedTo = new UserDTO(subscription.getSubscribedTo());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(UserDTO subscriber) {
        this.subscriber = subscriber;
    }

    public UserDTO getSubscribedTo() {
        return subscribedTo;
    }

    public void setSubscribedTo(UserDTO subscribedTo) {
        this.subscribedTo = subscribedTo;
    }
}
