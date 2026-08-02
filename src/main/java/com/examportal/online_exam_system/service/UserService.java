package com.examportal.online_exam_system.service;

import com.examportal.online_exam_system.dto.LoginRequest;
import com.examportal.online_exam_system.dto.RegisterRequest;
import com.examportal.online_exam_system.entity.User;
import com.examportal.online_exam_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ---------- REGISTRATION ----------
    public String registerUser(RegisterRequest request) {

        // Check karo email already exist toh nahi karta
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered!";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // encrypt
        user.setRole(request.getRole());

        userRepository.save(user);
        return "Registration successful!";
    }

    // ---------- LOGIN ----------
    public String loginUser(LoginRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return "User not found!";
        }

        User user = userOptional.get();

        // Encrypted password ko match karo
        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            return "Invalid password!";
        }

        return "Login successful! Role: " + user.getRole();
    }

    // ---------- GET ALL USERS (Admin ke liye) ----------
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }
}