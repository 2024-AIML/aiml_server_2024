package com.Member.aiml_server_2024.navigation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private final String apiKey = "AIzaSyAWxd0Oro0zSSYhUMaKnlf1rOf-3O_tOhI";
    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public String getGeocode(double latitude, double longitude) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("latlng", latitude + "," + longitude)
                .queryParam("key", apiKey)
                .queryParam("language", "ko");

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        return response;
    }

    @Override
    public String getAddressFromGeocode(double latitude, double longitude) {
        String response = getGeocode(latitude, longitude);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);
            JsonNode results = root.path("results");
            if (results.isArray() && results.size() > 0) {
                JsonNode addressComponents = results.get(0).path("formatted_address");
                return addressComponents.asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        return null;
    }
}
