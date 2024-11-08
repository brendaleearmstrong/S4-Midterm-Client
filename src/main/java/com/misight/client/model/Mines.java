package com.misight.client.model;

import java.util.HashSet;
import java.util.Set;


public class Mines {
    private Long id;
    private String name;
    private String company;
    private String location;
    private Provinces province;
    private Set<Minerals> minerals = new HashSet<>();
    private Set<EnvironmentalData> environmentalData = new HashSet<>();
    private Set<SafetyData> safetyData = new HashSet<>();

    public Mines() {}

    public Mines(String name, String company, String location, Provinces province) {
        this.name = name;
        this.company = company;
        this.location = location;
        this.province = province;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Provinces getProvince() { return province; }
    public void setProvince(Provinces province) { this.province = province; }

    public Set<Minerals> getMinerals() { return minerals; }
    public void setMinerals(Set<Minerals> minerals) { this.minerals = minerals; }

    public Set<EnvironmentalData> getEnvironmentalData() { return environmentalData; }
    public void setEnvironmentalData(Set<EnvironmentalData> environmentalData) {
        this.environmentalData = environmentalData;
    }

    public Set<SafetyData> getSafetyData() { return safetyData; }
    public void setSafetyData(Set<SafetyData> safetyData) { this.safetyData = safetyData; }
}
