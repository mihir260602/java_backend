package com.Event.Event.model;

import java.util.List;

// import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    // private List<Integer> eventList;

    // @ElementCollection(fetch = FetchType.EAGER)
    // @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    // @Column(name = "role")
    private String roles;
    @OneToMany(mappedBy = "user")
    private List<PasswordResetToken> passwordResetTokens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PasswordResetToken> getPasswordResetTokens() {
        return passwordResetTokens;
    }

    public void setPasswordResetTokens(List<PasswordResetToken> passwordResetTokens) {
        this.passwordResetTokens = passwordResetTokens;
    }

    // public List<Integer> getEventList() {
    //     return eventList;
    // }

    // public void setEventList(List<Integer> eventList) {
    //     this.eventList = eventList;
    // }
    // Getters and setters
}
