package com.hackerrank.github.repository;

import com.hackerrank.github.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrderByIdAsc();
    List<Event> findByActor_IdOrderByIdAsc(Long actorId);
}
