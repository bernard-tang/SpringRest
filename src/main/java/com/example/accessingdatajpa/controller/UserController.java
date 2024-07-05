package com.example.accessingdatajpa.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.accessingdatajpa.AccessingDataJpaApplication;
import com.example.accessingdatajpa.entity.User;
import com.example.accessingdatajpa.repository.UserRepository;
import com.example.accessingdatajpa.util.jwt.JwtUtil;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login1")
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
    
    
    @PostMapping("/login")
    public ResponseEntity<String> login1(@RequestBody Map<String, String> payload) {
    	
    	if(payload.get("username").isEmpty() && payload.get("password").isEmpty())
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    	
    	String username = payload.get("username");
    	String password = payload.get("password");
    	
    	log.info("blacklist token " + username);
    	
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
    
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract token from Authorization header (format: "Bearer <token>")
        String token = authorizationHeader.substring(7); // Skip "Bearer " prefix
        log.info("blacklist token " + token);
        // Invalidate the token by adding it to the blacklist
        jwtUtil.addToBlacklist(token);
    }

    // Other endpoints (e.g., registration) can be added here
}
