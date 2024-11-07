package com.misight.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class MonitoringStations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Provinces province;

    @ManyToMany
    @JoinTable(
            name = "station_pollutants",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "pollutant_id")
    )
    private List<Pollutants> pollutants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Provinces getProvince() {
        return province;
    }

    public void setProvince(Provinces province) {
        this.province = province;
    }

    public List<Pollutants> getPollutants() {
        return pollutants;
    }

    public void setPollutants(List<Pollutants> pollutants) {
        this.pollutants = pollutants;
    }
}
