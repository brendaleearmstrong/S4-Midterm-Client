package com.misight.service;

import com.misight.model.MonitoringStations;
import com.misight.model.Pollutants;
import com.misight.repository.MonitoringStationsRepo;
import com.misight.repository.PollutantsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitoringStationsService {

    @Autowired
    private MonitoringStationsRepo monitoringStationsRepo;

    @Autowired
    private PollutantsRepo pollutantsRepo;

    public MonitoringStations createStation(MonitoringStations station) {
        return monitoringStationsRepo.save(station);
    }

    public List<MonitoringStations> getAllStations() {
        return monitoringStationsRepo.findAll();
    }

    public MonitoringStations getStationById(Long id) {
        return monitoringStationsRepo.findById(id).orElse(null);
    }

    public MonitoringStations updateStation(Long id, MonitoringStations stationDetails) {
        Optional<MonitoringStations> station = monitoringStationsRepo.findById(id);
        if (station.isPresent()) {
            MonitoringStations existingStation = station.get();
            existingStation.setLocation(stationDetails.getLocation());
            existingStation.setProvince(stationDetails.getProvince());
            existingStation.setPollutants(stationDetails.getPollutants());
            return monitoringStationsRepo.save(existingStation);
        }
        return null;
    }

    public String deleteStation(Long id) {
        if (monitoringStationsRepo.existsById(id)) {
            monitoringStationsRepo.deleteById(id);
            return "Monitoring station deleted successfully.";
        }
        return "Monitoring station not found.";
    }

    public List<MonitoringStations> getStationsByProvince(Long provinceId) {
        return monitoringStationsRepo.findByProvinceId(provinceId);
    }

    public List<MonitoringStations> getStationsByLocation(String location) {
        return monitoringStationsRepo.findByLocation(location);
    }

    public MonitoringStations updateStationPollutants(Long stationId, List<Long> pollutantIds) {
        Optional<MonitoringStations> station = monitoringStationsRepo.findById(stationId);
        if (station.isPresent()) {
            List<Pollutants> pollutants = pollutantsRepo.findAllById(pollutantIds);
            MonitoringStations updatedStation = station.get();
            updatedStation.setPollutants(pollutants);
            return monitoringStationsRepo.save(updatedStation);
        }
        return null;
    }

    public List<Pollutants> getStationPollutants(Long stationId) {
        Optional<MonitoringStations> station = monitoringStationsRepo.findById(stationId);
        return station.map(MonitoringStations::getPollutants).orElse(null);
    }
}
