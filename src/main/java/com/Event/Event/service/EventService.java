package com.Event.Event.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Event.Event.model.Event;
import com.Event.Event.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEventsByUsername(String username) {
        return eventRepository.findByUsername(username);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            Event updatedEvent = event.get();
            updatedEvent.setName(eventDetails.getName());
            updatedEvent.setDescription(eventDetails.getDescription());
            updatedEvent.setDate(eventDetails.getDate());
            updatedEvent.setLocation(eventDetails.getLocation());
            return eventRepository.save(updatedEvent);
        } else {
            throw new RuntimeException("Event not found with id " + id);
        }
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

}
