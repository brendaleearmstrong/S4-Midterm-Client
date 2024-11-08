package com.misight.client.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private Set<Privileges> privileges = new HashSet<>();
    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<Privileges> getPrivileges() { return privileges; }
    public void setPrivileges(Set<Privileges> privileges) { this.privileges = privileges; }
    public void addPrivilege(Privileges privilege) {
        this.privileges.add(privilege);
        privilege.getUsers().add(this);
    }
    public void removePrivilege(Privileges privilege) {
        this.privileges.remove(privilege);
        privilege.getUsers().remove(this);
    }
}
