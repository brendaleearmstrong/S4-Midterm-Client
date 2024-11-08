package com.misight.client.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Privileges {
    private Long id;
    private String name;
    private Set<User> users = new HashSet<>();

    public Privileges() {}

    public Privileges(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }
}