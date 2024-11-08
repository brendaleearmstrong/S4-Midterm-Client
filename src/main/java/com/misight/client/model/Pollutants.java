package com.misight.client.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Pollutants {
    private Long id;
    private String name;
    private String category;
    private String unit;
    private Double benchmarkValue;
    private String benchmarkType;
    private String description;
    private String measurementFrequency;
    private Set<MonitoringStations> monitoringStations = new HashSet<>();
    private Set<EnvironmentalData> measurements = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Pollutants() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getBenchmarkValue() { return benchmarkValue; }
    public void setBenchmarkValue(Double value) { this.benchmarkValue = value; }

    public String getBenchmarkType() { return benchmarkType; }
    public void setBenchmarkType(String type) { this.benchmarkType = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMeasurementFrequency() { return measurementFrequency; }
    public void setMeasurementFrequency(String frequency) { this.measurementFrequency = frequency; }

    public Set<MonitoringStations> getMonitoringStations() { return monitoringStations; }
    public void setMonitoringStations(Set<MonitoringStations> stations) { this.monitoringStations = stations; }

    public Set<EnvironmentalData> getMeasurements() { return measurements; }
    public void setMeasurements(Set<EnvironmentalData> measurements) { this.measurements = measurements; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}