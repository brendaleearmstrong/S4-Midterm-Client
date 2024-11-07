package com.misight.service;

import com.misight.model.Mines;
import com.misight.model.Minerals;
import com.misight.repository.MinesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class MinesService {

    @Autowired
    private MinesRepo minesRepo;

    public Mines createMine(Mines mine) {
        return minesRepo.save(mine);
    }

    public Optional<Mines> getMineById(Long id) {
        return minesRepo.findById(id);
    }

    public Iterable<Mines> getAllMines() {
        return minesRepo.findAll();
    }

    public Mines updateMine(Long id, Mines mineDetails) {
        return minesRepo.findById(id).map(mine -> {
            mine.setName(mineDetails.getName());
            mine.setLocation(mineDetails.getLocation());
            mine.setMinerals(mineDetails.getMinerals());
            return minesRepo.save(mine);
        }).orElseGet(() -> {
            mineDetails.setId(id);
            return minesRepo.save(mineDetails);
        });
    }

    public void deleteMine(Long id) {
        minesRepo.deleteById(id);
    }

    public Mines updateMineMinerals(Long mineId, Set<Minerals> minerals) {
        return minesRepo.findById(mineId).map(mine -> {
            mine.setMinerals(minerals);
            return minesRepo.save(mine);
        }).orElse(null);
    }
}
