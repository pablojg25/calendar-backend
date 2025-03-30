package com.pablo.calendar_backend.service;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.auth.service.AuthService;
import com.pablo.calendar_backend.dto.UserDTO;
import com.pablo.calendar_backend.entity.User;
import com.pablo.calendar_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public ResponseEntity<ApiRes<UserDTO>> findUser() {
        String username = authService.getAuthenticatedUsername();
        User user = userRepository.findByUsername(username).orElse(null);
        String message = user != null ? "User found" : "User not found";
        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        UserDTO body = user != null ? new UserDTO(user) : null;
        ApiRes<UserDTO> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<UserDTO>> changeVisibility() {
        String username = authService.getAuthenticatedUsername();
        User user = userRepository.findByUsername(username).orElse(null);
        String message = "User not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        UserDTO body = null;
        if (user != null) {
            user.setVisible(!user.getVisible());
            message = "User visibility updated";
            status = HttpStatus.OK;
            body = new UserDTO(userRepository.save(user));
        }
        ApiRes<UserDTO> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<Void>> deleteUser() {
        String username = authService.getAuthenticatedUsername();
        User user = userRepository.findByUsername(username).orElse(null);
        String message = "User not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (user != null) {
            message = "User deleted";
            status = HttpStatus.OK;
            userRepository.delete(user);
        }
        ApiRes<Void> response = new ApiRes<>(
                status.value(),
                message,
                null
        );
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<ApiRes<UserDTO>> findUserByEmail(String email) {
        User user = userRepository.findByEmailAndVisibleIsTrue(email).orElse(null);
        String message = "User not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        UserDTO body = null;
        if (user != null) {
            message = "Can't search yourself";
            status = HttpStatus.BAD_REQUEST;
            if (!user.getUsername().equals(authService.getAuthenticatedUsername())) {
                message = "User found";
                status = HttpStatus.OK;
                body = new UserDTO(user);
            }
        }
        ApiRes<UserDTO> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }
}
