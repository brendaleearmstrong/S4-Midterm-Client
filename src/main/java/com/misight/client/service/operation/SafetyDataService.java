package com.misight.service;

import com.misight.model.SafetyData;
import com.misight.model.Mines;
import com.misight.exception.ResourceNotFoundException;
import com.misight.repository.SafetyDataRepo;
import com.misight.repository.MinesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class SafetyDataService {

    private final SafetyDataRepo safetyDataRepo;
    private final MinesRepo minesRepo;

    @Autowired
    public SafetyDataService(SafetyDataRepo safetyDataRepo, MinesRepo minesRepo) {
        this.safetyDataRepo = safetyDataRepo;
        this.minesRepo = minesRepo;
    }

    // Create a new SafetyData entry
    public SafetyData createSafetyData(Long mineId, SafetyData safetyData) {
        Mines mine = minesRepo.findById(mineId)
                .orElseThrow(() -> new ResourceNotFoundException("Mine not found with ID: " + mineId));

        safetyData.setMine(mine);
        safetyData.setSafetyLevel(calculateSafetyLevel(
                safetyData.getLostTimeIncidents(),
                safetyData.getNearMisses()
        ));

        return safetyDataRepo.save(safetyData);
    }

    // Retrieve SafetyData by ID
    public SafetyData getSafetyDataById(Long id) {
        return safetyDataRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Safety data not found with ID: " + id));
    }

    // Retrieve all SafetyData records
    public List<SafetyData> getAllSafetyData() {
        return safetyDataRepo.findAll();
    }

    // Retrieve SafetyData records for a specific mine
    public List<SafetyData> getSafetyDataByMine(Long mineId) {
        if (!minesRepo.existsById(mineId)) {
            throw new ResourceNotFoundException("Mine not found with ID: " + mineId);
        }
        return safetyDataRepo.findByMineId(mineId);
    }

    // Retrieve SafetyData records within a specific date range for a mine
    public List<SafetyData> getSafetyDataByDateRange(Long mineId, LocalDate startDate, LocalDate endDate) {
        if (!minesRepo.existsById(mineId)) {
            throw new ResourceNotFoundException("Mine not found with ID: " + mineId);
        }
        return safetyDataRepo.findByMineIdAndDateRecordedBetween(mineId, startDate, endDate);
    }

    // Update SafetyData by ID
    public SafetyData updateSafetyData(Long id, SafetyData safetyDataDetails) {
        SafetyData safetyData = getSafetyDataById(id);

        safetyData.setDateRecorded(safetyDataDetails.getDateRecorded());
        safetyData.setLostTimeIncidents(safetyDataDetails.getLostTimeIncidents());
        safetyData.setNearMisses(safetyDataDetails.getNearMisses());
        safetyData.setSafetyLevel(calculateSafetyLevel(
                safetyDataDetails.getLostTimeIncidents(),
                safetyDataDetails.getNearMisses()
        ));

        return safetyDataRepo.save(safetyData);
    }

    // Delete SafetyData by ID
    public void deleteSafetyData(Long id) {
        if (!safetyDataRepo.existsById(id)) {
            throw new ResourceNotFoundException("Safety data not found with ID: " + id);
        }
        safetyDataRepo.deleteById(id);
    }

    // Calculate safety level based on incidents and near misses
    private SafetyData.SafetyLevel calculateSafetyLevel(int lostTimeIncidents, int nearMisses) {
        if (lostTimeIncidents > 0) {
            return SafetyData.SafetyLevel.NEEDS_IMPROVEMENT;
        } else if (nearMisses > 2) {
            return SafetyData.SafetyLevel.NEEDS_IMPROVEMENT;
        } else if (nearMisses > 0) {
            return SafetyData.SafetyLevel.FAIR;
        } else {
            return SafetyData.SafetyLevel.GOOD;
        }
    }
}
