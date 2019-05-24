package com.hackerrank.github.model;

import javax.persistence.*;

@Entity
@Table(name = "REPO")
public class Repo {
    @Id
    @Column(name = "REPO_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "URL")
    private String url;

    public Repo() {
    }

    public Repo(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
