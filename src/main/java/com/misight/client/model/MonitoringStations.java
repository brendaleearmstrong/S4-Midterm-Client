package com.misight.client.model;

import java.util.List;
import java.util.ArrayList;

public class MonitoringStations {
    private Long id;
    private String location;
    private Provinces province;
    private List<Pollutants> pollutants = new ArrayList<>();

    public MonitoringStations() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Provinces getProvince() { return province; }
    public void setProvince(Provinces province) { this.province = province; }

    public List<Pollutants> getPollutants() { return pollutants; }
    public void setPollutants(List<Pollutants> pollutants) { this.pollutants = pollutants; }
}
