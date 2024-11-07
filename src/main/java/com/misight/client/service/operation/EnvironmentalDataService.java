package com.misight.service;

import com.misight.model.EnvironmentalData;
import com.misight.repository.EnvironmentalDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnvironmentalDataService {

    private final EnvironmentalDataRepo repo;

    @Autowired
    public EnvironmentalDataService(EnvironmentalDataRepo repo) {
        this.repo = repo;
    }

    public List<EnvironmentalData> getAllData() {
        return repo.findAll();
    }

    public Optional<EnvironmentalData> getDataById(Long id) {
        return repo.findById(id);
    }

    public List<EnvironmentalData> getDataByStation(Long stationId) {
        return repo.findByMonitoringStationId(stationId);
    }

    public List<EnvironmentalData> getDataByMine(Long mineId) {
        return repo.findByMineId(mineId);
    }

    public List<EnvironmentalData> getDataByDateRange(LocalDateTime start, LocalDateTime end) {
        return repo.findByMeasurementDateBetween(start, end);
    }

    public EnvironmentalData createData(EnvironmentalData data) {
        return repo.save(data);
    }

    public Optional<EnvironmentalData> updateData(Long id, EnvironmentalData data) {
        if (!repo.existsById(id)) {
            return Optional.empty();
        }
        data.setId(id);
        return Optional.of(repo.save(data));
    }

    public boolean deleteData(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}