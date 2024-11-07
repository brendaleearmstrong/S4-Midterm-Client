package com.misight.service;

import com.misight.model.Pollutants;
import com.misight.repository.PollutantsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PollutantsService {
    private final PollutantsRepo repo;

    @Autowired
    public PollutantsService(PollutantsRepo repo) {
        this.repo = repo;
    }

    public List<Pollutants> getAllPollutants() {
        return repo.findAll();
    }

    public Optional<Pollutants> getPollutantById(Long id) {
        return repo.findById(id);
    }

    public List<Pollutants> getPollutantsByName(String name) {
        return repo.findByNameIgnoreCase(name);
    }

    public List<Pollutants> getPollutantsByCategory(String category) {
        return repo.findByCategory(category);
    }

    public Pollutants createPollutant(Pollutants pollutant) {
        if (repo.existsByName(pollutant.getName())) {
            throw new IllegalArgumentException("Pollutant with name " + pollutant.getName() + " already exists");
        }
        return repo.save(pollutant);
    }

    public Optional<Pollutants> updatePollutant(Long id, Pollutants pollutant) {
        if (!repo.existsById(id)) {
            return Optional.empty();
        }
        pollutant.setId(id);
        return Optional.of(repo.save(pollutant));
    }

    public boolean deletePollutant(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
