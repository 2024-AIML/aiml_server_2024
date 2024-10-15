package com.Member.aiml_server_2024.navigation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeocodingServiceImplTest {

    @Autowired
    private GeocodingServiceImpl geocodingService;

    @Test
    void 주소확인() {
        double lat = 37.54776;
        double log = 126.9230;

        String address = geocodingService.getAddressFromGeocode(lat, log);

        System.out.println(address);
    }

}