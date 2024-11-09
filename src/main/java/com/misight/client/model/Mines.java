package com.misight.client.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mines")
public class Mines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String company;
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Provinces province;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "mine_minerals",
            joinColumns = @JoinColumn(name = "mine_id"),
            inverseJoinColumns = @JoinColumn(name = "mineral_id")
    )
    private Set<Minerals> minerals = new HashSet<>();

    @OneToMany(mappedBy = "mine")
    private Set<EnvironmentalData> environmentalData = new HashSet<>();

    @OneToMany(mappedBy = "mine")
    private Set<SafetyData> safetyData = new HashSet<>();

    public Mines() {}

    public Mines(String name, String company, String location, Provinces province) {
        this.name = name;
        this.company = company;
        this.location = location;
        this.province = province;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Set<Minerals> getMinerals() {
        return minerals;
    }

    public void setMinerals(Set<Minerals> minerals) {
        this.minerals = minerals;
    }

    public Set<EnvironmentalData> getEnvironmentalData() {
        return environmentalData;
    }

    public void setEnvironmentalData(Set<EnvironmentalData> environmentalData) {
        this.environmentalData = environmentalData;
    }

    public Set<SafetyData> getSafetyData() {
        return safetyData;
    }

    public void setSafetyData(Set<SafetyData> safetyData) {
        this.safetyData = safetyData;
    }
}