package com.Member.aiml_server_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Member.aiml_server_2024.service.ShelterService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/shelters")
public class ShelterController {
    @Autowired
    private ShelterService shelterService;

    @GetMapping("/addresses")
    public List<String> getShelterAddresses() throws ExecutionException, InterruptedException{
        return shelterService.getShelterAddresses();
    }
}
