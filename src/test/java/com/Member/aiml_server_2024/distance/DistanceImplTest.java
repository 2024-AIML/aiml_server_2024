package com.Member.aiml_server_2024.distance;

import com.Member.aiml_server_2024.AppConfig;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistanceImplTest {

    Distance distance = new DistanceImpl();

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        distance = appConfig.distance();
    }

    @Test
    void distance() {
        //given
        double lat1 = 37.55146;
        double lon1 = 126.9255; // 홍익대학교
        double lat2 = 37.54776;
        double lon2 = 126.9230; // 상수역

        //when
        double result = distance.distance(lat1, lon1, lat2, lon2);

        //join
        System.out.println("dist = " + result);
    }

}