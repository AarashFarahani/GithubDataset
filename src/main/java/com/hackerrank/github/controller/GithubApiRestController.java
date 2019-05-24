package com.hackerrank.github.controller;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GithubApiRestController {
    @Autowired private ActorRepository actorRepository;
    @Autowired private EventRepository eventRepository;

    @PostMapping("/events")
    public void addEvent(@RequestBody Event event) {
        this.eventRepository.save(event);
    }

    @GetMapping("/events/actors/{actorId}")
    public Actor getActor(@PathVariable Long actorId) {
        return this.actorRepository.findOne(actorId);
    }

    @GetMapping("/actors/streak")
    public List<Actor> getActors() {
        return this.actorRepository.findAll();
    }

    @PutMapping("/actors")
    public void updateActor(@RequestBody Actor actor) {
        this.actorRepository.save(actor);
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return this.eventRepository.findAll();
    }

    @GetMapping("/actors")
    public List<Actor> allActors() {
        return this.actorRepository.findAll();
    }

    @DeleteMapping("/erase")
    public void erase() {
        this.eventRepository.deleteAll();
    }
}
