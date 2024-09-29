// package com.Event.Event.controller;

// import java.security.Principal;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.Event.Event.model.Event;
// import com.Event.Event.model.EventRegistration;
// import com.Event.Event.model.User;
// import com.Event.Event.service.EventRegistrationService;
// import com.Event.Event.service.*;

// @RestController
// @RequestMapping("/api/event-registrations")
// public class EventRegistrationController {

//     @Autowired
//     private EventRegistrationService eventRegistrationService;

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private EventService eventService;

//     // Register for an event
//     @PostMapping("/register/{eventId}")
//     public ResponseEntity<?> registerForEvent(@PathVariable Long eventId, Principal principal) {
//         User currentUser = userService.findByUsername(principal.getName()).orElse(null);
//         Event event = eventService.getEventById(eventId).orElse(null);

//         if (currentUser == null || event == null) {
//             return ResponseEntity.badRequest().body("User or Event not found.");
//         }

//         // Register user for the event
//         EventRegistration registration = new EventRegistration();
//         registration.setEvent(event);
//         registration.setUser(currentUser);
//         eventRegistrationService.registerForEvent(currentUser, event); // Use the service method to save

//         return ResponseEntity.ok("User registered for the event successfully.");
//     }

//     // Get all events a user is registered for
//     @GetMapping("/user-registrations")
//     public ResponseEntity<List<Event>> getUserRegisteredEvents(Principal principal) {
//         User currentUser = userService.findByUsername(principal.getName()).orElse(null);

//         if (currentUser == null) {
//             return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
//         }

//         List<Event> registeredEvents = eventRegistrationService.getEventsByUser(currentUser);
//         return ResponseEntity.ok(registeredEvents);
//     }
// }

package com.Event.Event.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Event.Event.model.Event;
import com.Event.Event.model.EventRegistration;
import com.Event.Event.model.User;
import com.Event.Event.service.EventRegistrationService;
import com.Event.Event.service.EventService;
import com.Event.Event.service.UserService;

@RestController
@RequestMapping("/api/event-registrations")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    // Register for an event
    @PostMapping("/register/{eventId}")
    public ResponseEntity<?> registerForEvent(@PathVariable Long eventId, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElse(null);
        Event event = eventService.getEventById(eventId).orElse(null);

        if (currentUser == null || event == null) {
            return ResponseEntity.badRequest().body("User or Event not found.");
        }

        // Register user for the event
        EventRegistration registration = new EventRegistration();
        registration.setEvent(event);
        registration.setUser(currentUser);
        eventRegistrationService.registerForEvent(currentUser, event); // Use the service method to save

        return ResponseEntity.ok("User registered for the event successfully.");
    }

    @GetMapping("/user-registrations")
    public ResponseEntity<List<Event>> getUserRegisteredEvents(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElse(null);
    
        if (currentUser == null) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
        }
    
        List<Event> registeredEvents = eventRegistrationService.getEventsByUser(currentUser);
        return ResponseEntity.ok(registeredEvents);
    }
    
    @GetMapping("/event/{eventId}/registrations")
    public ResponseEntity<List<Map<String, Object>>> getRegistrationsForEvent(@PathVariable Long eventId, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElse(null);
        Event event = eventService.getEventById(eventId).orElse(null);
    
        if (currentUser == null || event == null) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
        }
    
        // Check if the current user is the creator of the event
        if (!event.getUsername().equals(currentUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Return 403 Forbidden
        }
    
        List<Map<String, Object>> registrations = eventRegistrationService.getRegistrationsByEvent(event);
        return ResponseEntity.ok(registrations);
    }
    

    // @GetMapping("/event/{eventId}/registrations")
    // public ResponseEntity<List<EventRegistration>> getRegistrationsForEvent(@PathVariable Long eventId, Principal principal) {
    //     User currentUser = userService.findByUsername(principal.getName()).orElse(null);
    //     Event event = eventService.getEventById(eventId).orElse(null);
    
    //     if (currentUser == null || event == null) {
    //         return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
    //     }
    
    //     // Check if the current user is the creator of the event using the username
    //     if (!event.getUsername().equals(currentUser.getUsername())) {
    //         return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Return 403 Forbidden
    //     }
    
    //     List<EventRegistration> registrations = eventRegistrationService.getRegistrationsByEvent(event);
    //     return ResponseEntity.ok(registrations);
    // }
    

    // // Get all events a user is registered for
    // @GetMapping("/user-registrations")
    // public ResponseEntity<List<Event>> getUserRegisteredEvents(Principal principal) {
    //     User currentUser = userService.findByUsername(principal.getName()).orElse(null);

    //     if (currentUser == null) {
    //         return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
    //     }

    //     List<Event> registeredEvents = eventRegistrationService.getEventsByUser(currentUser);
    //     return ResponseEntity.ok(registeredEvents);
    // }

    // // Get users registered for a specific event
    // @GetMapping("/event/{eventId}/registrations")
    // public ResponseEntity<List<EventRegistration>> getRegistrationsForEvent(@PathVariable Long eventId, Principal principal) {
    //     User currentUser = userService.findByUsername(principal.getName()).orElse(null);
    //     Event event = eventService.getEventById(eventId).orElse(null);

    //     if (currentUser == null || event == null) {
    //         return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
    //     }

    //     // Check if the current user is the creator of the event using the username
    //     if (!event.getUsername().equals(currentUser.getUsername())) {
    //         return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Return 403 Forbidden
    //     }

    //     List<EventRegistration> registrations = eventRegistrationService.getRegistrationsByEvent(event);
    //     return ResponseEntity.ok(registrations);
    // }
}
