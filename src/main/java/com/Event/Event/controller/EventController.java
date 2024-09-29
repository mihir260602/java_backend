package com.Event.Event.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Event.Event.model.Event;
import com.Event.Event.model.User;
import com.Event.Event.service.EventService;
import com.Event.Event.service.UserService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:5713/")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    private static final int MAX_DESCRIPTION_LENGTH = 5000; // Define a max length for descriptions

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        if (event.getDescription() != null && event.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            return ResponseEntity.badRequest().body("Description is too long");
        }
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElse(null);
        Optional<Event> existingEvent = eventService.getEventById(id);

        // Admin can modify any event, user can only modify their own events
        if (currentUser != null && existingEvent.isPresent()) {
            if ("ADMIN".equals(currentUser.getRoles())
                    || existingEvent.get().getUsername().equals(currentUser.getUsername())) {
                eventDetails.setId(existingEvent.get().getId()); // Set ID to update
                Event updatedEvent = eventService.updateEvent(id, eventDetails);
                return ResponseEntity.ok(updatedEvent);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You do not have permission to modify this event.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found.");
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        // Optionally truncate long descriptions
        events.forEach(event -> {
            if (event.getDescription() != null && event.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
                event.setDescription(event.getDescription().substring(0, MAX_DESCRIPTION_LENGTH) + "...");
            }
        });
        return ResponseEntity.ok(events);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Event>> getEventsByUsername(@PathVariable String username) {
        List<Event> events = eventService.getEventsByUsername(username);
        // Optionally truncate long descriptions
        events.forEach(event -> {
            if (event.getDescription() != null && event.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
                event.setDescription(event.getDescription().substring(0, MAX_DESCRIPTION_LENGTH) + "...");
            }
        });
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(e -> {
            if (e.getDescription() != null && e.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
                e.setDescription(e.getDescription().substring(0, MAX_DESCRIPTION_LENGTH) + "...");
            }
            return ResponseEntity.ok(e);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
    // eventService.deleteEvent(id);
    // return ResponseEntity.noContent().build();
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElse(null);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Return 403 without body
        }

        if (currentUser.getRoles().equals("ADMIN")) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            // Check if the event belongs to the user
            Optional<Event> existingEventOptional = eventService.getEventById(id);
            if (existingEventOptional.isPresent()) {
                Event existingEvent = existingEventOptional.get();
                if (existingEvent.getUsername().equals(currentUser.getUsername())) {
                    eventService.deleteEvent(id);
                    return ResponseEntity.noContent().build(); // 204 No Content
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
                }
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        }
    }
}
