package com.hackerrank.github.repository;

import com.hackerrank.github.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrderByIdAsc();
    @Query(value = "SELECT * FROM EVENT A WHERE FK_ACTOR = :actorId ORDER BY EVENT_ID",
            nativeQuery = true)
    List<Event> findByActorIdOrderByIdAsc(@Param("actorId") Long actorId);
    List<Event> findByActor_IdOrderByIdAsc(Long actorId);
}
