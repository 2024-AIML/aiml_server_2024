//package com.Member.aiml_server_2024.controller;
//
//import com.Member.aiml_server_2024.service.ProximityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/proximity")
//public class ProximityController {
//
//    @Autowired
//    private ProximityService proximityService;
//
//    @PostMapping("/check")
//    public String checkProximity(@RequestParam String userLocation, @RequestParam String shelterLocation) {
//        proximityService.checkCount(userLocation, shelterLocation);
//        return "Proximity check completed";
//    }
//}
