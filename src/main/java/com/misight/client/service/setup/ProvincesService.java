package com.misight.service;

import com.misight.model.Provinces;
import com.misight.repository.ProvincesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincesService {

    @Autowired
    private ProvincesRepo provinceRepo;

    public List<Provinces> getAllProvinces() {
        return provinceRepo.findAll();
    }

    public Provinces getProvinceById(Long id) {
        return provinceRepo.findById(id).orElse(null);
    }

    public Provinces createProvince(Provinces province) {
        return provinceRepo.save(province);
    }

    public Provinces updateProvince(Long id, Provinces province) {
        if (provinceRepo.existsById(id)) {
            province.setId(id);
            return provinceRepo.save(province);
        }
        return null;
    }

    public void deleteProvince(Long id) {
        provinceRepo.deleteById(id);
    }
}
