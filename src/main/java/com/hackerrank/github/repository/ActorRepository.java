package com.hackerrank.github.repository;

import com.hackerrank.github.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query(value = "SELECT * FROM Actor A ORDER BY\n" +
            " (SELECT COUNT(*) FROM EVENT E WHERE A.ACTOR_ID = E.FK_ACTOR) DESC, \n" +
            " (SELECT MAX(CREATEDAT) FROM EVENT E WHERE A.ACTOR_ID = E.FK_ACTOR) DESC,\n" +
            " A.LOGIN ASC",
            nativeQuery = true)
    List<Actor> findAllBaseOnEventsCount();
}
