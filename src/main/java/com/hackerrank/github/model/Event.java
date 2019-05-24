package com.hackerrank.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hackerrank.github.utils.CustomTimeStampDeserializer;
import com.hackerrank.github.utils.CustomTimeStampSerializer;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @Column(name = "EVENT_ID")
    private Long id;
    @Column(name = "TYPE")
    private String type;

    @JoinColumn(name = "ACTOR_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private Actor actor;

    @JoinColumn(name = "REPO_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private Repo repo;

    @JsonSerialize(using = CustomTimeStampSerializer.class)
    @JsonDeserialize(using = CustomTimeStampDeserializer.class)
    @JsonProperty("created_at")
    @Column(name = "CREATEDAT")
    private Timestamp createdAt;

    public Event() {
    }

    public Event(Long id, String type, Actor actor, Repo repo, Timestamp createdAt) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
