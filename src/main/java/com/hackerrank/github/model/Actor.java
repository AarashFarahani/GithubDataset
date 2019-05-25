package com.hackerrank.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACTOR")
public class Actor {
    @Id
    @Column(name = "ACTOR_ID")
    private Long id;
    @Column(name = "LOGIN")
    private String login;
    @JsonProperty("avatar_url")
    @Column(name = "AVATAR")
    private String avatar;

//    @OneToMany(mappedBy="actor", cascade = CascadeType.ALL)
//    Set<Event> event = new HashSet<>();

    public Actor() {
    }

    public Actor(Long id, String login, String avatar) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
