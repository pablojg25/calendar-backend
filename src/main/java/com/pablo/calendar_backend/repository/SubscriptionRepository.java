package com.pablo.calendar_backend.repository;

import com.pablo.calendar_backend.entity.Subscription;
import com.pablo.calendar_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription save(Subscription subscription);

    List<Subscription> findBySubscriber(User subscriber);

    boolean existsBySubscriberAndSubscribedTo(User subscriber, User subscribedTo);

    void deleteBySubscriberAndSubscribedTo(User subscriber, User subscribedTo);

}
