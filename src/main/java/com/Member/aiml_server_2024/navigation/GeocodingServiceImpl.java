package com.Member.aiml_server_2024.navigation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Value;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private final String apiKey = "AIzaSyAWxd0Oro0zSSYhUMaKnlf1rOf-3O_tOhI";

//    @Value("${google.api.key}")gi
//    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @SneakyThrows
    public static String URLEn(String str) {
        String url = URLEncoder.encode(str, "UTF-8");
        return url;
    }


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

    @Override
    public void savaLocation(UserLocation userLocation) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection("USER_LOCATION").document(userLocation.getId()).set(userLocation);
    }

    @Override
    public String getLocations(String userId, String shelterName) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        // USER_LOCATION에서 해당 사용자의 정보 가져오기
        DocumentReference userLocationRef = db.collection("USER_LOCATION").document(userId);
        ApiFuture<DocumentSnapshot> userLocationFuture = userLocationRef.get();
        DocumentSnapshot userLocationDoc = userLocationFuture.get();

        UserLocation userLocation = null;
        if (userLocationDoc.exists()) {
            userLocation = userLocationDoc.toObject(UserLocation.class);
        }

        // Shelter 컬렉션에서 sheltername으로 해당 문서 가져오기
//        DocumentReference shelterRef = db.collection("Shelter").document("홍익대학교");
//        ApiFuture<DocumentSnapshot> shelterFuture = shelterRef.get();
//        DocumentSnapshot shelterDoc = shelterFuture.get();
        ApiFuture<QuerySnapshot> shelterQueryFuture = db.collection("Shelter")
                .whereEqualTo("sheltername", shelterName)
                .get();
        QuerySnapshot shelterQuerySnapshot = shelterQueryFuture.get();

        Shelter shelter = null;
        if (!shelterQuerySnapshot.getDocuments().isEmpty()) {
            DocumentSnapshot shelterDoc = shelterQuerySnapshot.getDocuments().get(0);
            shelter = shelterDoc.toObject(Shelter.class);
        }

        if (shelter == null) {
            return "Shelter not found";
        }

        // URL 인코딩
        String encodedLocationName = URLEn(userLocation.getLocationName());
        String encodedShelterName = URLEn(shelter.getSheltername());

        // URL 생성
        String url = String.format("nmap://route/walk?slat=%s&slng=%s&sname=%s&dlat=%s&dlng=%s&dname=%s&appname=com.example.aiml_mobile_2024",
                userLocation.getLatitude(), userLocation.getLatitude(), encodedLocationName,
                shelter.getLatitude(), shelter.getLongitude(), encodedShelterName);

        return url;
    }
}
