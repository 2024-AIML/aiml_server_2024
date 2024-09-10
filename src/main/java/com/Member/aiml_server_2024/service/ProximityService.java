//package com.Member.aiml_server_2024.service;
//
//import com.Member.aiml_server_2024.distance.DistanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ExecutionException;
//
//@Service
//public class ProximityService {
//    @Autowired
//    private DistanceService distanceService;
//
//    public void checkCount(String userLoc, String shelterLoc){
//        try{
//            distanceService.updateShelterCount(userLoc, shelterLoc);
//        }
//        catch (ExecutionException | InterruptedException e){
//            e.printStackTrace();
//        }
//    }
//}
