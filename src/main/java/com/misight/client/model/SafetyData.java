package com.misight.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "safety_data")
public class SafetyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mine_id", nullable = false)
    private Mines mine;

    @Column(nullable = false)
    private LocalDate dateRecorded;

    private int lostTimeIncidents;
    private int nearMisses;

    @Enumerated(EnumType.STRING)
    private SafetyLevel safetyLevel;

    public enum SafetyLevel {
        GOOD, FAIR, NEEDS_IMPROVEMENT
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
