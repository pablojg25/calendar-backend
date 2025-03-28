package com.pablo.calendar_backend.repository;

import com.pablo.calendar_backend.entity.UserNotification;
import com.pablo.calendar_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,Long> {

    Optional<UserNotification> findByIdAndUser(Long id, User user);

    List<UserNotification> findByUserOrderByDate(User user);

    UserNotification save(UserNotification notification);

    void delete(UserNotification notification);

    void deleteByUser(User user);

}
