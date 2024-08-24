package com.Member.aiml_server_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Member.aiml_server_2024.service.ShelterService;
import com.Member.aiml_server_2024.model.MakeShelterList;
import com.Member.aiml_server_2024.model.Shelter;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/shelters")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @Autowired
    private MakeShelterList makeShelterList;

    // 기존 주소 리스트를 반환하는 엔드포인트
    @GetMapping("/addresses")
    public List<String> getShelterAddresses() throws ExecutionException, InterruptedException{
        return shelterService.getShelterAddresses();
    }

    // 새로운 좌표 반환 엔드포인트 추가
    @GetMapping("/coordinates")
    public List<Shelter> getShelterCoordinates(@RequestParam String address) throws ExecutionException, InterruptedException {
        return makeShelterList.getSheltersByPartialAddress(address);
    }
}
