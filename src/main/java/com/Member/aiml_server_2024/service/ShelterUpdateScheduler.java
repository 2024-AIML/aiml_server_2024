package com.Member.aiml_server_2024.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShelterUpdateScheduler {
    @Autowired
    private CountingService countingService;

    @Scheduled(fixedRate = 60000)
    public void updateShelters(){
        try {
            countingService.updateOccupiedUsersInShelters();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
