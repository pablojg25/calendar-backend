package com.pablo.calendar_backend.auth.service;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.auth.dto.AuthResponse;
import com.pablo.calendar_backend.auth.dto.LoginRequest;
import com.pablo.calendar_backend.auth.dto.RegisterRequest;
import com.pablo.calendar_backend.entity.User;
import com.pablo.calendar_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<ApiRes<AuthResponse>> register(RegisterRequest request) {
        /*HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "El nombre de usuario y/o email ya están en uso";
        AuthResponse body = null;
        if (!userRepository.existsByUsername(request.getUsername()) && !userRepository.existsByEmail(request.getEmail())) {
            User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            status = HttpStatus.CREATED;
            message = "Usuario registrado correctamente";
            body = new AuthResponse(jwtService.getToken(user));
        }
        ApiRes<AuthResponse> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.ok(response);*/
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "El nombre de usuario y/o email ya están en uso";
        AuthResponse body = null;

        try {
            if (!userRepository.existsByUsername(request.getUsername()) && !userRepository.existsByEmail(request.getEmail())) {
                User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));

                System.out.println("Usuario a registrar: " + user);  // Log para ver el objeto antes de guardar
                userRepository.save(user);

                status = HttpStatus.CREATED;
                message = "Usuario registrado correctamente";
                body = new AuthResponse(jwtService.getToken(user));
            }
        } catch (Exception e) {
            message = "Hubo un error al registrar el usuario: " + e.getMessage();
            e.printStackTrace();  // Verifica los logs para cualquier excepción.
        }

        ApiRes<AuthResponse> response = new ApiRes<>(status.value(), message, body);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiRes<AuthResponse>> login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "El usuario no se encontró";
        AuthResponse body = null;
        if (user != null) {
            String token = jwtService.getToken(user);
            status = HttpStatus.OK;
            message = "Usuario encontrado";
            body = new AuthResponse(token);
        }
        ApiRes<AuthResponse> response = new ApiRes<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.ok(response);
    }

    public String getAuthenticatedUsername () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }

}
