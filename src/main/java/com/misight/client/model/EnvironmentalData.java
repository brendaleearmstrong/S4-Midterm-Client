package com.misight.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "environmental_data")
public class EnvironmentalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pollutant_id", nullable = false)
    private Pollutants pollutant;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private MonitoringStations monitoringStation;

    @ManyToOne
    @JoinColumn(name = "mine_id", nullable = false)
    private Mines mine;

    @Column(name = "measured_value", nullable = false)
    private Double measuredValue;

    @Column(name = "measurement_date", nullable = false)
    private LocalDateTime measurementDate;

    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public EnvironmentalData() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Pollutants getPollutant() { return pollutant; }
    public void setPollutant(Pollutants pollutant) { this.pollutant = pollutant; }

    public MonitoringStations getMonitoringStation() { return monitoringStation; }
    public void setMonitoringStation(MonitoringStations station) { this.monitoringStation = station; }

    public Mines getMine() { return mine; }
    public void setMine(Mines mine) { this.mine = mine; }

    public Double getMeasuredValue() { return measuredValue; }
    public void setMeasuredValue(Double value) { this.measuredValue = value; }

    public LocalDateTime getMeasurementDate() { return measurementDate; }
    public void setMeasurementDate(LocalDateTime date) { this.measurementDate = date; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}