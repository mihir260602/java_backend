// package com.Event.Event.model;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Lob;

// @Entity
// public class Event {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private String name;
// 	@Lob
//     private String description;
//     private String date;
//     private String location;
//     private String username; // Store the username of the creator
// 	public Event() {
//     }
// 	public Long getId() {
// 		return id;
// 	}
// 	public void setId(Long id) {
// 		this.id = id;
// 	}
// 	public String getName() {
// 		return name;
// 	}
// 	public void setName(String name) {
// 		this.name = name;
// 	}
// 	public String getDescription() {
// 		return description;
// 	}
// 	public void setDescription(String description) {
// 		this.description = description;
// 	}
// 	public String getDate() {
// 		return date;
// 	}
// 	public void setDate(String date) {
// 		this.date = date;
// 	}
// 	public String getLocation() {
// 		return location;
// 	}
// 	public void setLocation(String location) {
// 		this.location = location;
// 	}
// 	public String getUsername() {
// 		return username;
// 	}
// 	public void setUsername(String username) {
// 		this.username = username;
// 	}
// 	public Event(Long id, String name, String description, String date, String location, String username) {
// 		this.id = id;
// 		this.name = name;
// 		this.description = description;
// 		this.date = date;
// 		this.location = location;
// 		this.username = username;
// 	}

//     // Getters and Setters
//     // Constructor(s)
// }

package com.Event.Event.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Lob
    private String description;
    private String date;
    private String location;
    private String username; // Store the username of the creator
    
    // Add this field to represent the registrations
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventRegistration> registrations = new HashSet<>();

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<EventRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<EventRegistration> registrations) {
        this.registrations = registrations;
    }

    public Event(Long id, String name, String description, String date, String location, String username) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.username = username;
    }

    // Additional methods to manage registrations if needed
    public void addRegistration(EventRegistration registration) {
        registrations.add(registration);
        registration.setEvent(this);
    }

    public void removeRegistration(EventRegistration registration) {
        registrations.remove(registration);
        registration.setEvent(null);
    }
}
