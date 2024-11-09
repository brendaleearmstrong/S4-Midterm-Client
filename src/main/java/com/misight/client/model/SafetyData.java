package com.misight.client.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@Entity
@Table(name = "safety_data")
public class SafetyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mine_id", nullable = true)
    @JsonProperty("mine_id")
    private Mines mine;

    @Column(name = "date_recorded", nullable = false)
    @JsonProperty("dateRecorded")
    private LocalDate dateRecorded;

    @Column(name = "lost_time_incidents")
    @JsonProperty("lostTimeIncidents")
    private int lostTimeIncidents;

    @Column(name = "near_misses")
    @JsonProperty("nearMisses")
    private int nearMisses;

    @Enumerated(EnumType.STRING)
    @Column(name = "safety_level")
    @JsonProperty("safetyLevel")
    private SafetyLevel safetyLevel;

    public enum SafetyLevel {
        GOOD, FAIR, NEEDS_IMPROVEMENT, EXCELLENT, CRITICAL
    }

    public SafetyData() {}

    public SafetyData(Mines mine, LocalDate dateRecorded, int lostTimeIncidents, int nearMisses, SafetyLevel safetyLevel) {
        this.mine = mine;
        this.dateRecorded = dateRecorded;
        this.lostTimeIncidents = lostTimeIncidents;
        this.nearMisses = nearMisses;
        this.safetyLevel = safetyLevel;
    }

    public Long getId() { return id; }
    public Mines getMine() { return mine; }
    public LocalDate getDateRecorded() { return dateRecorded; }
    public int getLostTimeIncidents() { return lostTimeIncidents; }
    public int getNearMisses() { return nearMisses; }
    public SafetyLevel getSafetyLevel() { return safetyLevel; }

    public void setId(Long id) { this.id = id; }
    public void setMine(Mines mine) { this.mine = mine; }
    public void setDateRecorded(LocalDate dateRecorded) { this.dateRecorded = dateRecorded; }
    public void setLostTimeIncidents(int lostTimeIncidents) { this.lostTimeIncidents = lostTimeIncidents; }
    public void setNearMisses(int nearMisses) { this.nearMisses = nearMisses; }
    public void setSafetyLevel(SafetyLevel safetyLevel) { this.safetyLevel = safetyLevel; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SafetyData)) return false;
        SafetyData that = (SafetyData) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
