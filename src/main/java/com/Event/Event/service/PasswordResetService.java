package com.Event.Event.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Event.Event.model.PasswordResetToken;
import com.Event.Event.model.User;
import com.Event.Event.repository.PasswordResetTokenRepository;
import com.Event.Event.repository.UserRepository;
import com.Event.Event.exception.InvalidTokenException;

@Service
public class PasswordResetService {
    
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository; // Add this line to inject UserRepository

    public void resetPassword(String token, String newPassword) throws InvalidTokenException {
        validatePasswordResetToken(token);

        // Use Optional to retrieve the PasswordResetToken
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Token not found"));

        User user = passwordResetToken.getUser();
        user.setPassword(newPassword); // Ensure this is hashed
        
        userRepository.save(user); // Use userRepository to save the updated user
    }

    public void validatePasswordResetToken(String token) throws InvalidTokenException {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid or expired token."));

        if (passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Invalid or expired token.");
        }
    }
}
