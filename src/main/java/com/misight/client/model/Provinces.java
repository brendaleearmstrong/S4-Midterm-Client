package com.misight.client.model;

import java.util.HashSet;
import java.util.Set;

public class Provinces {
    private Long id;
    private String name;
    private String region;
    private Set<Mines> mines = new HashSet<>();
    private Set<MonitoringStations> monitoringStations = new HashSet<>();

    public Provinces() {}

    public Provinces(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public Set<Mines> getMines() { return mines; }
    public void setMines(Set<Mines> mines) { this.mines = mines; }
    public Set<MonitoringStations> getMonitoringStations() { return monitoringStations; }
    public void setMonitoringStations(Set<MonitoringStations> stations) { this.monitoringStations = stations; }
}