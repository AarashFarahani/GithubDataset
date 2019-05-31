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

    @Query(value = "WITH DATES\n" +
            "     AS (  SELECT FK_ACTOR, MAX(TO_DATE (CREATEDAT, 'YYYY-MM-DD')) CREATEDAT\n" +
            "             FROM EVENT\n" +
            "         GROUP BY FK_ACTOR),\n" +
            "     GROUPS\n" +
            "     AS (SELECT ROW_NUMBER () OVER (PARTITION BY FK_ACTOR ORDER BY CREATEDAT) AS RN,\n" +
            "                DATEADD (DAY, -ROW_NUMBER () OVER (PARTITION BY FK_ACTOR ORDER BY CREATEDAT), CREATEDAT)\n" +
            "                   AS GRP,\n" +
            "                CREATEDAT, FK_ACTOR\n" +
            "           FROM DATES),\n" +
            "     STREAK\n" +
            "     AS (  SELECT COUNT (*) AS CONSECUTIVEDATES,\n" +
            "                  MAX (CREATEDAT) AS MAXDATE,\n" +
            "                  MAX(FK_ACTOR) FK_ACTOR\n" +
            "             FROM GROUPS\n" +
            "         GROUP BY GRP\n" +
            "         ORDER BY 1 DESC, 2 DESC),\n" +
            "     ACTORS\n" +
            "     AS (  SELECT A.*\n" +
            "             FROM ACTOR A\n" +
            "                  JOIN\n" +
            "                  (  SELECT MAX (E.CREATEDAT) CREATEDAT,\n" +
            "                            MIN (E.FK_ACTOR) FK_ACTOR\n" +
            "                       FROM EVENT E\n" +
            "                   GROUP BY E.FK_ACTOR) IE\n" +
            "                     ON IE.FK_ACTOR = A.ACTOR_ID\n" +
            "                  JOIN\n" +
            "                  (  SELECT MAX (S.CONSECUTIVEDATES) CONSECUTIVEDATES, S.FK_ACTOR\n" +
            "                       FROM STREAK S\n" +
            "                   GROUP BY S.FK_ACTOR) JS\n" +
            "                     ON JS.FK_ACTOR = A.ACTOR_ID\n" +
            "         ORDER BY JS.CONSECUTIVEDATES DESC, IE.CREATEDAT DESC, A.LOGIN)\n" +
            "SELECT *\n" +
            "  FROM ACTORS",
            nativeQuery = true)
    List<Actor> findAllBasedOnStreak();
}
