package com.misight.service;

import com.misight.model.Minerals;
import com.misight.repository.MineralsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MineralsService {

    @Autowired
    private MineralsRepo repo;

    // Retrieve all minerals
    public List<Minerals> getAllMinerals() {
        return repo.findAll();
    }

    // Retrieve a specific mineral by ID
    public Minerals getMineralById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Create a new mineral
    public Minerals createMineral(Minerals mineral) {
        return repo.save(mineral);
    }

    // Update an existing mineral by ID
    public Minerals updateMineral(Long id, Minerals mineral) {
        Optional<Minerals> existingMineral = repo.findById(id);
        if (existingMineral.isPresent()) {
            mineral.setId(id);
            return repo.save(mineral);
        } else {
            return null;
        }
    }

    // Delete a mineral by ID
    public void deleteMineral(Long id) {
        repo.deleteById(id);
    }
}

