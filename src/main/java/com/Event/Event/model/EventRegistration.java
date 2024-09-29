// package com.Event.Event.model;

// // import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;
// @Entity
// public class EventRegistration {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private User user;
    
//     @ManyToOne
//     private Event event;
    
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

//     public Event getEvent() {
//         return event;
//     }

//     public void setEvent(Event event) {
//         this.event = event;
//     }
    
// }

package com.Event.Event.model;

import javax.persistence.*;

@Entity
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    
    @ManyToOne
    private Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
