// Pollutants.java
package com.misight.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pollutants")
public class Pollutants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String unit;

    @Column(name = "benchmark_value", nullable = false)
    private Double benchmarkValue;

    @Column(name = "benchmark_type", nullable = false)
    private String benchmarkType;

    @Column(length = 1000)
    private String description;

    @Column(name = "measurement_frequency", nullable = false)
    private String measurementFrequency;

    @ManyToMany(mappedBy = "pollutants")
    private Set<MonitoringStations> monitoringStations = new HashSet<>();

    @OneToMany(mappedBy = "pollutant")
    private Set<EnvironmentalData> measurements = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Pollutants() {}

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
}