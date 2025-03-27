package com.pablo.calendar_backend.service;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.dto.UserNotificationRequest;
import com.pablo.calendar_backend.dto.UserNotificationResponse;
import com.pablo.calendar_backend.entity.NotificationType;
import com.pablo.calendar_backend.entity.User;
import com.pablo.calendar_backend.entity.UserNotification;
import com.pablo.calendar_backend.repository.UserNotificationRepository;
import com.pablo.calendar_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserNotificationService {

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ApiRes<List<UserNotificationResponse>>> findUserNotifications() {
        Long userId = 0L;
        User found = userRepository.findById(userId).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Usuario no encontrado";
        List<UserNotificationResponse> body = null;
        if (found != null) {
            List<UserNotification> foundNotifs = userNotificationRepository.findByUser(found);
            body = new ArrayList<>();
            for (UserNotification notification : foundNotifs) {
                body.add(new UserNotificationResponse(notification));
            }
            status = HttpStatus.OK;
            message = "Listado de notificaciones";
        }
        ApiRes<List<UserNotificationResponse>> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<UserNotificationResponse>> createNotification(UserNotificationRequest request) {
        Long userId = 0L;
        User found = userRepository.findById(userId).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Usuario no encontrado";
        UserNotificationResponse body = null;
        if (found != null) {
            NotificationType type = NotificationType.fromString(request.getType());
            status = HttpStatus.BAD_REQUEST;
            message = "Tipo de notificación no válido";
            if (type != null) {
                UserNotification newNotif = new UserNotification();
                newNotif.setTitle(request.getTitle());
                newNotif.setContent(request.getContent());
                newNotif.setDate(request.getDate());
                newNotif.setType(type);
                newNotif.setUser(found);
                body = new UserNotificationResponse(userNotificationRepository.save(newNotif));
                status = HttpStatus.CREATED;
                message = "Notificación creada";
            }
        }
        ApiRes<UserNotificationResponse> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<UserNotificationResponse>> findNotificationById(Long notifId) {
        Long userId = 0L;
        User user = userRepository.findById(userId).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Usuario no encontrado";
        UserNotificationResponse body = null;
        if (user != null) {
            UserNotification found = userNotificationRepository.findByIdAndUser(notifId,user).orElse(null);
            body = found != null ? new UserNotificationResponse(found) : null;
            message = body != null ? "Notificación encontrada" : "Notificación no encontrada";
            status = body != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        }
        ApiRes<UserNotificationResponse> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<UserNotificationResponse>> updateNotification(Long notifId, UserNotificationRequest request) {
        Long userId = 0L;
        User user = userRepository.findById(userId).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Usuario no encontrado";
        UserNotificationResponse body = null;
        if (user != null) {
            UserNotification found = userNotificationRepository.findByIdAndUser(notifId,user).orElse(null);
            message = "Notificación no encontrada";
            if (found != null) {
                NotificationType type = NotificationType.fromString(request.getType());
                status = HttpStatus.BAD_REQUEST;
                message = "Tipo de notificación no válido";
                if (type != null) {
                    found.setTitle(request.getTitle());
                    found.setContent(request.getContent());
                    found.setType(type);
                    found.setDate(request.getDate());
                    body = new UserNotificationResponse(userNotificationRepository.save(found));
                    status = HttpStatus.OK;
                    message = "Notificación actualizada";
                }
            }
        }
        ApiRes<UserNotificationResponse> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<Void>> deleteNotification(Long notifId) {
        Long userId = 0L;
        User user = userRepository.findById(userId).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Usuario no encontrado";
        if (user != null) {
            UserNotification found = userNotificationRepository.findByIdAndUser(notifId,user).orElse(null);
            message = "Notificación no encontrada";
            if (found != null) {
                userNotificationRepository.deleteById(notifId);
                status = HttpStatus.OK;
                message = "Notificación eliminada";
            }
        }
        ApiRes<Void> response = new ApiRes<>(
                status.value(),
                message,
                null
        );
        return ResponseEntity.status(status).body(response);
    }

}
