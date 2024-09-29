// // package com.Event.Event.service;

// // import java.time.LocalDateTime;
// // import java.util.Collections;
// // import java.util.Optional;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.stereotype.Service;

// // import com.Event.Event.model.PasswordResetToken;
// // import com.Event.Event.model.User;
// // import com.Event.Event.repository.UserRepository;

// // @Service
// // public class UserService {
// //     @Autowired
// //     private UserRepository userRepository;

// //     @Autowired
// //     private PasswordEncoder passwordEncoder;

// //     @Autowired
// //     private MailService mailService;

// //     public User registerUser(User user) {
// //         user.setPassword(passwordEncoder.encode(user.getPassword()));
// //         //user.setRoles(Collections.singleton("ROLE_USER")); // Default role
// //         if (user.getEmail().equals("admin1@example.com") || user.getEmail().equals("admin2@example.com")) {
// //             user.setRole("ADMIN");
// //         } else {
// //             user.setRole("USER");
// //         }
// //         User savedUser = userRepository.save(user);

// //         // Send registration email
// //         mailService.sendRegistrationEmail(user.getEmail(), user.getUsername());

// //         return savedUser;
// //     }
// //     public void createPasswordResetTokenForUser(User user, String token) {
// //         PasswordResetToken resetToken = new PasswordResetToken();
// //         resetToken.setToken(token);
// //         resetToken.setUser(user);
// //         resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Set token expiry
// //         passwordResetTokenRepository.save(resetToken);
// //     }
// //     public Optional<User> loginUser(String username, String password) {
// //         return userRepository.findByUsername(username)
// //                               .filter(user -> passwordEncoder.matches(password, user.getPassword()));
// //     }

// //     public Optional<User> findByUsername(String username) {
// //         return userRepository.findByUsername(username);
// //     }

// //     public Optional<User> findById(Long id) {
// //         return userRepository.findById(id);
// //     }

// //     public User assignAdminRole(Long userId) {
// //         User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
// //         user.setRoles(Collections.singleton("ROLE_ADMIN"));
// //         return userRepository.save(user);
// //     }
// // }

// package com.Event.Event.service;

// import java.time.LocalDateTime;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.Event.Event.model.PasswordResetToken;
// import com.Event.Event.model.User;
// import com.Event.Event.repository.PasswordResetTokenRepository; // Import this for password reset token repository
// import com.Event.Event.repository.UserRepository;

// @Service
// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private MailService mailService;

//     @Autowired
//     private PasswordResetTokenRepository passwordResetTokenRepository; // Autowire the token repository

//     // Method for registering the user and assigning roles
//     public User registerUser(User user) {
//         user.setPassword(passwordEncoder.encode(user.getPassword()));

//         // Role assignment based on the email address
//         if (user.getEmail().equals("mihirjoshi2602@gmail.com") || user.getEmail().equals("mihirjoshi260602@gmail.com")) {
//             user.setRoles("ADMIN");
//         } else {
//             user.setRoles("USER");
//         }

//         User savedUser = userRepository.save(user);

//         // Send registration email to the user
//         mailService.sendRegistrationEmail(user.getEmail(), user.getUsername());

//         return savedUser;
//     }

//     // Create a password reset token for the user
//     public void createPasswordResetTokenForUser(User user, String token) {
//         PasswordResetToken resetToken = new PasswordResetToken();
//         resetToken.setToken(token);
//         resetToken.setUser(user);
//         resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token expiry set to 1 hour
//         passwordResetTokenRepository.save(resetToken); // Save the token in the repository
//     }

//     // Method for logging in the user by verifying password
//     public Optional<User> loginUser(String username, String password) {
//         return userRepository.findByUsername(username)
//                               .filter(user -> passwordEncoder.matches(password, user.getPassword()));
//     }

//     // Find a user by username
//     public Optional<User> findByUsername(String username) {
//         return userRepository.findByUsername(username);
//     }

//     // Find a user by ID
//     public Optional<User> findById(Long id) {
//         return userRepository.findById(id);
//     }

//     public Optional<User> findByEmail(String email) {
//         return userRepository.findByEmail(email);
//     }

//     // Assign the admin role to a specific user by ID
//     public User assignAdminRole(Long userId) {
//         User user = userRepository.findById(userId)
//             .orElseThrow(() -> new RuntimeException("User not found"));
//         user.setRoles("ADMIN");
//         return userRepository.save(user);
//     }
// }

// package com.Event.Event.service;

// import java.time.LocalDateTime;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.Event.Event.model.PasswordResetToken;
// import com.Event.Event.model.User;
// import com.Event.Event.repository.PasswordResetTokenRepository;
// import com.Event.Event.repository.UserRepository;

// @Service
// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     // @Autowired
//     // private MailService mailService;

//     @Autowired
//     private PasswordResetTokenRepository passwordResetTokenRepository;

//     // Method for registering the user and assigning roles
//     public User registerUser(User user) {
//         user.setPassword(passwordEncoder.encode(user.getPassword()));

//         if (user.getEmail().equals("mihirjoshi2602@gmail.com") || user.getUsername().equals("mj")
//                 || user.getEmail().equals("mihirjoshi260602@gmail.com")) {
//             user.setRoles("ADMIN");
//         } else {
//             user.setRoles("USER");
//         }

//         return userRepository.save(user);
//     }

//     // Create a password reset token for the user
//     public void createPasswordResetTokenForUser(User user, String token) {
//         PasswordResetToken resetToken = new PasswordResetToken();
//         resetToken.setToken(token);
//         resetToken.setUser(user);
//         resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token expiry set to 1 hour
//         passwordResetTokenRepository.save(resetToken); // Save the token in the repository
//     }

//     // public Optional<User> loginUser(String username, String password) {
//     // return userRepository.findByUsername(username)
//     // .filter(user -> passwordEncoder.matches(password, user.getPassword()));
//     // }

//     public Optional<User> loginUser(String username, String password) {
//         Optional<User> userOptional = userRepository.findByUsername(username);

//         // Check if the user exists
//         if (userOptional.isPresent()) {
//             User user = userOptional.get();

//             // If the user is an admin, allow login without a password
//             if (user.getRoles().equals("ADMIN")) {
//                 return Optional.of(user); // Return the user directly
//             }

//             // For regular users, check the password
//             if (passwordEncoder.matches(password, user.getPassword())) {
//                 return Optional.of(user); // Return the user if password matches
//             }
//         }

//         return Optional.empty(); // Return empty if login fails
//     }

//     public Optional<User> findByUsername(String username) {
//         return userRepository.findByUsername(username);
//     }

//     public Optional<User> findByEmail(String email) {
//         return userRepository.findByEmail(email);
//     }

//     public Optional<PasswordResetToken> findByResetToken(String token) {
//         return passwordResetTokenRepository.findByToken(token);
//     }

//     public void updatePassword(User user, String newPassword) {
//         user.setPassword(passwordEncoder.encode(newPassword));
//         userRepository.save(user); // Save the updated user with the new password
//     }

//     public void deletePasswordResetToken(PasswordResetToken token) {
//         passwordResetTokenRepository.delete(token); // Remove the token after the password reset
//     }

//     public boolean resetPassword(String token, String newPassword) {
//         Optional<PasswordResetToken> optionalToken = passwordResetTokenRepository.findByToken(token);
        
//         // Log whether the token was found
//         System.out.println("Token found: " + optionalToken.isPresent());
        
//         if (optionalToken.isPresent()) {
//             PasswordResetToken resetToken = optionalToken.get();
            
//             // Log the expiry date of the token
//             System.out.println("Token expiry date: " + resetToken.getExpiryDate());
            
//             if (resetToken.getExpiryDate().isAfter(LocalDateTime.now())) {
//                 User user = resetToken.getUser();
//                 user.setPassword(passwordEncoder.encode(newPassword)); // Encode new password
//                 userRepository.save(user); // Save the updated user
//                 passwordResetTokenRepository.delete(resetToken); // Optionally delete the used token
//                 return true; // Indicate success
//             }
//         }
//         return false; // Indicate failure (invalid/expired token)
//     }
    
//     // public boolean resetPassword(String token, String newPassword) {
//     // // Find the reset token in the repository
//     // Optional<PasswordResetToken> resetTokenOpt =
//     // passwordResetTokenRepository.findByToken(token);

//     // // Check if the token exists and is valid (not expired)
//     // if (resetTokenOpt.isPresent()) {
//     // PasswordResetToken resetToken = resetTokenOpt.get();

//     // // Check if the token is expired
//     // if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//     // return false; // Token has expired
//     // }

//     // // Retrieve the user associated with this token
//     // User user = resetToken.getUser();

//     // // Update the user's password and save it
//     // user.setPassword(passwordEncoder.encode(newPassword));
//     // userRepository.save(user);

//     // // Optionally, delete the token after successful password reset
//     // passwordResetTokenRepository.delete(resetToken);

//     // return true; // Password reset successful
//     // }

//     // return false; // Invalid token
//     // }

// }
package com.Event.Event.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Event.Event.exception.InvalidTokenException; // Ensure this exception is defined
import com.Event.Event.model.PasswordResetToken;
import com.Event.Event.model.User;
import com.Event.Event.repository.PasswordResetTokenRepository;
import com.Event.Event.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    // Method for registering the user and assigning roles
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getEmail().equals("mihirjoshi2602@gmail.com") || user.getUsername().equals("mj")
                || user.getEmail().equals("mihirjoshi260602@gmail.com")) {
            user.setRoles("ADMIN");
        } else {
            user.setRoles("USER");
        }

        return userRepository.save(user);
    }

    // Create a password reset token for the user
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token expiry set to 1 hour
        passwordResetTokenRepository.save(resetToken); // Save the token in the repository
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        // Check if the user exists
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // If the user is an admin, allow login without a password
            if (user.getRoles().equals("ADMIN")) {
                return Optional.of(user); // Return the user directly
            }

            // For regular users, check the password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user); // Return the user if password matches
            }
        }

        return Optional.empty(); // Return empty if login fails
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<PasswordResetToken> findByResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user); // Save the updated user with the new password
    }

    public void deletePasswordResetToken(PasswordResetToken token) {
        passwordResetTokenRepository.delete(token); // Remove the token after the password reset
    }

    public void resetPassword(String token, String newPassword) throws InvalidTokenException {
        // Validate the token and retrieve it
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid or expired token."));

        // Check if the token is expired
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Invalid or expired token.");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword)); // Encode new password
        userRepository.save(user); // Save the updated user
        passwordResetTokenRepository.delete(resetToken); // Optionally delete the used token
    }
}
