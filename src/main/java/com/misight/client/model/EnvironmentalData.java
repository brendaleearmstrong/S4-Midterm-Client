package com.misight.client.model;

import java.time.LocalDateTime;

public class EnvironmentalData {
    private Long id;
    private Pollutants pollutant;
    private MonitoringStations monitoringStation;
    private Mines mine;
    private Double measuredValue;
    private LocalDateTime measurementDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EnvironmentalData() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public EnvironmentalData(Pollutants pollutant, MonitoringStations station, Mines mine,
                             Double value, LocalDateTime measurementDate) {
        this();
        this.pollutant = pollutant;
        this.monitoringStation = station;
        this.mine = mine;
        this.measuredValue = value;
        this.measurementDate = measurementDate;
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
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
