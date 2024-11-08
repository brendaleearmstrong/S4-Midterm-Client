package com.misight.client.service;

import com.misight.client.exception.ServiceException;
import com.misight.client.service.operation.EnvironmentalDataService;
import com.misight.client.service.operation.SafetyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataVerificationService {
    private final SafetyDataService safetyDataService;
    private final EnvironmentalDataService environmentalDataService;

    @Autowired
    public DataVerificationService(SafetyDataService safetyDataService,
                                   EnvironmentalDataService environmentalDataService) {
        this.safetyDataService = safetyDataService;
        this.environmentalDataService = environmentalDataService;
    }

    public void verifyDataPersistence(Long dataId, String dataType) {
        boolean isPersisted = false;
        switch (dataType) {
            case "SAFETY":
                isPersisted = safetyDataService.verifyDataPersistence(dataId);
                break;
            case "ENVIRONMENTAL":
                isPersisted = environmentalDataService.verifyDataPersistence(dataId);
                break;
            default:
                throw new ServiceException("Unknown data type: " + dataType);
        }

        if (!isPersisted) {
            throw new ServiceException("Data verification failed for ID: " + dataId);
        }
    }

    public void verifySystemConnectivity() {
        try {
            safetyDataService.getAllData();
            environmentalDataService.getAllData();
            System.out.println("System connectivity verified successfully");
        } catch (Exception e) {
            throw new ServiceException("System connectivity check failed: " + e.getMessage());
        }
    }
}
