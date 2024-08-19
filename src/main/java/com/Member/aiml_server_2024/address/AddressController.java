package com.Member.aiml_server_2024.address;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")

public class AddressController {
    @PostMapping("/address")
    public ResponseEntity<String> receiveAddress(@RequestBody Map<String, String> request) {
        String address = request.get("address");
        System.out.println("Received address: " + address);
        return ResponseEntity.ok("Address received");
    }
}
