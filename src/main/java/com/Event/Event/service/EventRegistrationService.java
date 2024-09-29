package com.Event.Event.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Event.Event.model.Event;
import com.Event.Event.model.EventRegistration;
import com.Event.Event.model.User;
import com.Event.Event.repository.EventRegistrationRepository;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    public EventRegistration registerForEvent(User user, Event event) {
        EventRegistration registration = new EventRegistration();
        registration.setUser(user);
        registration.setEvent(event);
        return eventRegistrationRepository.save(registration);
    }

    public List<Event> getEventsByUser(User user) {
        List<EventRegistration> registrations = eventRegistrationRepository.findByUser(user);
        return registrations.stream()
                .map(EventRegistration::getEvent)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getRegistrationsByEvent(Event event) {
        List<EventRegistration> registrations = eventRegistrationRepository.findByEvent(event);

        // Return only specific fields from the User and Event
        return registrations.stream()
                .map(registration -> {
                    Map<String, Object> responseMap = new HashMap<>();

                    // Extracting specific fields from User
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("username", registration.getUser().getUsername());
                    userMap.put("name", registration.getUser().getName());
                    userMap.put("email", registration.getUser().getEmail());

                    // Add to the response
                    responseMap.put("user", userMap);

                    // Optionally include other fields from EventRegistration like event details if
                    // needed
                    responseMap.put("event", registration.getEvent().getName());

                    return responseMap;
                })
                .collect(Collectors.toList());
    }
}

// @Service
// public class EventRegistrationService {

// @Autowired
// private EventRegistrationRepository eventRegistrationRepository;

// public EventRegistration registerForEvent(User user, Event event) {
// EventRegistration registration = new EventRegistration();
// registration.setUser(user);
// registration.setEvent(event);
// return eventRegistrationRepository.save(registration);
// }

// public List<Event> getEventsByUser(User user) {
// List<EventRegistration> registrations =
// eventRegistrationRepository.findByUser(user);
// return registrations.stream()
// .map(EventRegistration::getEvent)
// .collect(Collectors.toList());
// }

// public List<EventRegistration> getRegistrationsByEvent(Event event) {
// return eventRegistrationRepository.findByEvent(event);
// }
// }

// @Service
// public class EventRegistrationService {

// @Autowired
// private EventRegistrationRepository eventRegistrationRepository;

// public EventRegistration registerForEvent(User user, Event event) {
// EventRegistration registration = new EventRegistration();
// registration.setUser(user);
// registration.setEvent(event);
// return eventRegistrationRepository.save(registration);
// }

// // Save method for event registration
// public EventRegistration save(EventRegistration registration) {
// return eventRegistrationRepository.save(registration);
// }

// // public List<Event> getEventsByUser(User user) {
// // return eventRegistrationRepository.findEventsByUser(user);
// // }

// public List<Event> getEventsByUser(User user) {
// List<EventRegistration> registrations =
// eventRegistrationRepository.findByUser(user);
// return registrations.stream()
// .map(EventRegistration::getEvent)
// .collect(Collectors.toList());
// }

// // Get registrations by event
// public List<EventRegistration> getRegistrationsByEvent(Event event) {
// return eventRegistrationRepository.findByEvent(event);
// }
// }
