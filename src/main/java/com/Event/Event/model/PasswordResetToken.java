// package com.Event.Event.model;

// import java.time.LocalDateTime;

// import javax.persistence.*;

// @Entity
// public class PasswordResetToken {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private String token;
//     private LocalDateTime expiryDate;

//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     private User user;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getToken() {
//         return token;
//     }

//     public void setToken(String token) {
//         this.token = token;
//     }

//     public LocalDateTime getExpiryDate() {
//         return expiryDate;
//     }

//     public void setExpiryDate(LocalDateTime expiryDate) {
//         this.expiryDate = expiryDate;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }
    
// }

package com.Event.Event.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String token;
    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id") // Clarify relationship with User
    private User user;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
