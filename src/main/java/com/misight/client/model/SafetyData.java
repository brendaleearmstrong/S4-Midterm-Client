package com.misight.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

public class SafetyData {
    private Long id;

    @JsonIgnoreProperties({"safetyData", "environmentalData", "minerals", "province"})
    private Mines mine;

    private LocalDate dateRecorded;
    private int lostTimeIncidents;
    private int nearMisses;

    public enum SafetyLevel {
        CRITICAL,
        FAIR,
        GOOD,
        EXCELLENT,
        NEEDS_IMPROVEMENT
    }

    private SafetyLevel safetyLevel;

    public SafetyData() {}

    public SafetyData(Mines mine, LocalDate dateRecorded, int lostTimeIncidents, int nearMisses, SafetyLevel safetyLevel) {
        this.mine = mine;
        this.dateRecorded = dateRecorded;
        this.lostTimeIncidents = lostTimeIncidents;
        this.nearMisses = nearMisses;
        this.safetyLevel = safetyLevel;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mines getMine() {
        return mine;
    }

    public void setMine(Mines mine) {
        this.mine = mine;
    }

    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(LocalDate dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public int getLostTimeIncidents() {
        return lostTimeIncidents;
    }

    public void setLostTimeIncidents(int lostTimeIncidents) {
        this.lostTimeIncidents = lostTimeIncidents;
    }

    public int getNearMisses() {
        return nearMisses;
    }

    public void setNearMisses(int nearMisses) {
        this.nearMisses = nearMisses;
    }

    public SafetyLevel getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(SafetyLevel safetyLevel) {
        this.safetyLevel = safetyLevel;
    }
}