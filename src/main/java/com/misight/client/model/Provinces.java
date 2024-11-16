package com.misight.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;

public class Provinces {
    private Long id;
    private String name;

    @JsonIgnoreProperties("province")
    private Set<Mines> mines = new HashSet<>();

    @JsonIgnoreProperties("province")
    private Set<MonitoringStations> monitoringStations = new HashSet<>();

    public Provinces() {}

    public Provinces(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Mines> getMines() { return mines; }
    public void setMines(Set<Mines> mines) { this.mines = mines; }

    public Set<MonitoringStations> getMonitoringStations() { return monitoringStations; }
    public void setMonitoringStations(Set<MonitoringStations> stations) { this.monitoringStations = stations; }
}