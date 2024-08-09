package com.Member.aiml_server_2024;

import com.Member.aiml_server_2024.distance.Distance;
import com.Member.aiml_server_2024.distance.DistanceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Distance distance() {
        return new DistanceImpl();
    }

}
