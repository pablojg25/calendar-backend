package com.pablo.calendar_backend.controller;

import com.pablo.calendar_backend.ApiRes;
import com.pablo.calendar_backend.dto.UserDTO;
import com.pablo.calendar_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiRes<UserDTO>> findUser() {
        return userService.findUser();
    }

    @PutMapping("")
    public ResponseEntity<ApiRes<UserDTO>> changeVisibility() {
        return userService.changeVisibility();
    }

    @DeleteMapping("")
    public ResponseEntity<ApiRes<Void>> deleteUser() {
        return userService.deleteUser();
    }

}
