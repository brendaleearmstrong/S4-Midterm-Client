package com.misight.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;

public class Minerals {
    private Long id;
    private String name;
    
    @JsonIgnoreProperties({"minerals", "safetyData", "environmentalData", "province"})
    private Set<Mines> mines = new HashSet<>();

    public Minerals() {}

    public Minerals(String name) {
        this.name = name;
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

    public Set<Mines> getMines() {
        return mines;
    }

    public void setMines(Set<Mines> mines) {
        this.mines = mines;
    }
}
