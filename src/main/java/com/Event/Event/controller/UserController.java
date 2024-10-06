package com.Event.Event.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Event.Event.config.JwtUtils;
import com.Event.Event.exception.InvalidTokenException;
import com.Event.Event.model.User;
import com.Event.Event.service.MailService;
import com.Event.Event.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5713/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private JwtUtils jwtUtils; // Inject JwtUtils

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        mailService.sendRegistrationEmail(user.getEmail(), user.getName());
        return ResponseEntity.ok("User registered successfully");
    }

    // @PostMapping("/login")
    // public ResponseEntity<String> loginUser(@RequestBody User user) {
    // Optional<User> foundUser;

    // // Check if the user is an admin first
    // if (user.getPassword() == null || user.getPassword().isEmpty()) {
    // foundUser = userService.findByUsername(user.getUsername());
    // System.out.println("Found user: " + user.getUsername() + " with roles: " +
    // user.getRoles());

    // if (foundUser.isPresent() && foundUser.get().getRoles().equals("ADMIN")) {
    // // Generate token for admin without password check
    // String token = jwtUtils.generateToken(foundUser.get().getUsername());
    // System.out.println("Admin login without password successful for user: " +
    // user.getUsername());

    // return ResponseEntity.ok(token);
    // } else {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid
    // credentials");
    // }
    // } else {
    // // Regular login flow for users with a password
    // foundUser = userService.loginUser(user.getUsername(), user.getPassword());
    // if (foundUser.isPresent()) {
    // String token = jwtUtils.generateToken(foundUser.get().getUsername());
    // return ResponseEntity.ok(token);
    // } else {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid
    // credentials");
    // }
    // }
    // }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        Optional<User> foundUser;

        // Check if the user is an admin first
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            foundUser = userService.findByUsername(user.getUsername());
            System.out.println("Found user: " + user.getUsername() + " with roles: " + user.getRoles());

            if (foundUser.isPresent() && foundUser.get().getRoles().equals("ADMIN")) {
                // Generate token for admin without password check
                String token = jwtUtils.generateToken(foundUser.get().getUsername());
                System.out.println("Admin login without password successful for user: " + user.getUsername());

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", "ADMIN");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            // Regular login flow for users with a password
            foundUser = userService.loginUser(user.getUsername(), user.getPassword());
            if (foundUser.isPresent()) {
                String token = jwtUtils.generateToken(foundUser.get().getUsername());
                String role = foundUser.get().getRoles(); // Get the user's role

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", role);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @PostMapping("/forgot-password")
    // public ResponseEntity<?> forgotPassword(@RequestParam String email) {
    // User user = userService.findByEmail(email).orElse(null);
    // if (user == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    // }

    // // Generate a token
    // String token = UUID.randomUUID().toString();
    // userService.createPasswordResetTokenForUser(user, token);

    // // Send email with the token
    // String resetUrl = "http://localhost:8080/user/reset-password?token=" + token;
    // mailService.sendPasswordResetEmail(user.getEmail(), resetUrl);

    // return ResponseEntity.ok("Password reset link sent to your email.");
    // }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required.");
        }

        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Generate a token
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        // Send email with the token
        // String resetUrl = "http://localhost:8080/api/users/reset-password?token=" +
        // token;
        String resetUrl = "http://localhost:5173/reset-password?token=" + token; // Use your frontend URL here

        mailService.sendPasswordResetEmail(user.getEmail(), resetUrl);

        return ResponseEntity.ok("Password reset link sent to your email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody Map<String, String> request) {
        System.out.println("Reset password request received for token: " + token); // Log the token

        String newPassword = request.get("password");

        try {
            userService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Password has been reset successfully.");
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
