package com.misight.client.model;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class SafetyData {
    private Long id;
    private Mines mine;
    private LocalDate dateRecorded;
    private int lostTimeIncidents;
    private int nearMisses;
    private SafetyLevel safetyLevel;

    public enum SafetyLevel {
        GOOD, FAIR, NEEDS_IMPROVEMENT
    }

    public SafetyData() {}

    public SafetyData(Mines mine, LocalDate dateRecorded, int lostTimeIncidents,
                      int nearMisses, SafetyLevel safetyLevel) {
        this.mine = mine;
        this.dateRecorded = dateRecorded;
        this.lostTimeIncidents = lostTimeIncidents;
        this.nearMisses = nearMisses;
        this.safetyLevel = safetyLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Mines getMine() { return mine; }
    public void setMine(Mines mine) { this.mine = mine; }

    public LocalDate getDateRecorded() { return dateRecorded; }
    public void setDateRecorded(LocalDate dateRecorded) { this.dateRecorded = dateRecorded; }

    public int getLostTimeIncidents() { return lostTimeIncidents; }
    public void setLostTimeIncidents(int lostTimeIncidents) {
        this.lostTimeIncidents = lostTimeIncidents;
    }

    public int getNearMisses() { return nearMisses; }
    public void setNearMisses(int nearMisses) { this.nearMisses = nearMisses; }

    public SafetyLevel getSafetyLevel() { return safetyLevel; }
    public void setSafetyLevel(SafetyLevel safetyLevel) { this.safetyLevel = safetyLevel; }
}
