package com.example.accessingdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.accessingdatajpa.entity.User;
import com.example.accessingdatajpa.repository.UserRepository;
import com.example.accessingdatajpa.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username.toUpperCase());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        
        if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
        	final String token = jwtUtil.generateToken(user);
        	return ResponseEntity.ok(token);
        }
        else
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");

//        if (passwordEncoder.matches(password, user.getPassword())) {
//            return ResponseEntity.ok("Login successful for " + username);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
    }

    // Other endpoints (e.g., registration) can be added here
}
