package com.hackerrank.github.controller;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GithubApiRestController {
    @Autowired private ActorRepository actorRepository;
    @Autowired private EventRepository eventRepository;

    @PostMapping("/events")
    public ResponseEntity addEvent(@RequestBody Event event) {
        try {
            this.eventRepository.save(event);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/events/actors/{actorId}")
    public ResponseEntity<List<Event>> getEvents(@PathVariable Long actorId) {
        List<Event> result = this.eventRepository.findByActor_IdOrderByIdAsc(actorId);
        if(result == null || result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/actors/streak")
    public List<Actor> getActors() {
        return this.actorRepository.findAll();
    }

    @PutMapping("/actors")
    public ResponseEntity updateActor(@RequestBody Actor actor) {
        Actor model = this.actorRepository.findOne(actor.getId());

        if(model == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        if(model.getLogin().equals(actor.getLogin()) == false)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.actorRepository.save(actor);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return this.eventRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/actors")
    public List<Actor> allActors() {
        return this.actorRepository.findAll();
    }

    @DeleteMapping("/erase")
    public ResponseEntity erase() {
        this.eventRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}
