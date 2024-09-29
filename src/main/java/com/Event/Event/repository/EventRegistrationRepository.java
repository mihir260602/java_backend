// package com.Event.Event.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.Event.Event.model.Event;
// import com.Event.Event.model.EventRegistration;
// import com.Event.Event.model.User;

// @Repository
// public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
//     List<Event> findEventsByUser(User user);
//     List<EventRegistration> findByUserId(Long userId);

// }

package com.Event.Event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Event.Event.model.Event;
import com.Event.Event.model.EventRegistration;
import com.Event.Event.model.User;

// @Repository
// public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
//     // List<Event> findEventsByUser(User user);
//     List<EventRegistration> findByUser(User user);
//     List<EventRegistration> findByUserId(Long userId);
//     List<EventRegistration> findByEvent(Event event); // Add this line
// }

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    List<EventRegistration> findByUser(User user);
    List<EventRegistration> findByEvent(Event event);
}
