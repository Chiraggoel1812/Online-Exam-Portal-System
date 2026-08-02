package com.examportal.online_exam_system.controller;

import com.examportal.online_exam_system.dto.LoginRequest;
import com.examportal.online_exam_system.dto.RegisterRequest;
import com.examportal.online_exam_system.entity.User;
import com.examportal.online_exam_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")   // frontend se call allow karne ke liye
public class UserController {

    @Autowired
    private UserService userService;

    // POST /api/users/register
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    // POST /api/users/login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    // GET /api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}