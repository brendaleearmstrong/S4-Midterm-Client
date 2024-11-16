package com.misight.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;

public class MonitoringStations {
    private Long id;
    private String location;

    @JsonIgnoreProperties("monitoringStations")
    private Provinces province;

    @JsonIgnoreProperties("monitoringStations")
    private Set<Pollutants> pollutants = new HashSet<>();

    @JsonIgnoreProperties("monitoringStation")
    private Set<EnvironmentalData> measurements = new HashSet<>();

    public MonitoringStations() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Provinces getProvince() { return province; }
    public void setProvince(Provinces province) { this.province = province; }

    public Set<Pollutants> getPollutants() { return pollutants; }
    public void setPollutants(Set<Pollutants> pollutants) { this.pollutants = pollutants; }

    public Set<EnvironmentalData> getMeasurements() { return measurements; }
    public void setMeasurements(Set<EnvironmentalData> measurements) { this.measurements = measurements; }
}