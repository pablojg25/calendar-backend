package com.pablo.calendar_backend.auth.controller;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.auth.dto.AuthResponse;
import com.pablo.calendar_backend.auth.dto.LoginRequest;
import com.pablo.calendar_backend.auth.dto.RegisterRequest;
import com.pablo.calendar_backend.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiRes<AuthResponse>> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiRes<AuthResponse>> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiRes<Void>> deleteUser() {
        return authService.deleteUser();
    }

}
