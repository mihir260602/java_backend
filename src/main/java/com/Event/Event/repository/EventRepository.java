package com.Event.Event.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Event.Event.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUsername(String username);
}
