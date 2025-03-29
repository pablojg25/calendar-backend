package com.pablo.calendar_backend.repository;

import com.pablo.calendar_backend.entity.UserNotification;
import com.pablo.calendar_backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,Long> {

    Optional<UserNotification> findByIdAndUser(Long id, User user);

    List<UserNotification> findByUserOrderByDate(User user);

    UserNotification save(UserNotification notification);

    void delete(UserNotification notification);

    void deleteByUser(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserNotification u WHERE user = :user AND date < :date")
    void deletePastUserNotifications(@Param("user") User user, @Param("date") LocalDate date);

}
