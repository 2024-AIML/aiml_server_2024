package com.Member.aiml_server_2024.navigation;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class navigationServiceImpl implements navigationScheme {

    @SneakyThrows
    public static String URLEn(String str){
//        String url = URLEncoder.encode("http://111.111.111.111:8080/search?param=가나다", "UTF-8");

        String url = URLEncoder.encode(str, "UTF-8");

        return url;
    }

    @Override
    public Map<String, Object> GetWalkRoute() {
        Map<String, Object> WalkRouteData = new HashMap<>();

        String start = "서울대학교";
        start = URLEn(start);
        double slat = 37.4640070; // 뒤에 0이 안보내짐
        double slng = 126.9522394;

        String destination = "올림픽공원";
        destination = URLEn(destination);
        double dlat = 37.5209436;
        double dlng = 127.1230074;

        String furl = "nmap://route/walk?";
        furl = furl + "slat=" + slat + "&slng=" + slng + "&sname=" + start + "&dlat=" + dlat + "&dlng=" + dlng + "&dname=" + destination + "&appname=com.example.aiml_mobile_2024";

//        WalkRouteData.put("label1", "nmap://route/walk?slat=37.4640070&slng=126.9522394&sname=%EC%84%9C%EC%9A%B8%EB%8C%80%ED%95%99%EA%B5%90&dlat=37.5209436&dlng=127.1230074&dname=%EC%98%AC%EB%A6%BC%ED%94%BD%EA%B3%B5%EC%9B%90&appname=com.example.aiml_mobile_2024");
//        WalkRouteData.put("label2", "nmap://route/walk?slat=37.4640070&slng=126.9522394&sname=%EC%84%9C%EC%9A%B8%EB%8C%80%ED%95%99%EA%B5%90&dlat=37.4764356&dlng=126.9618302&dname=%EB%8F%99%EC%9B%90%EB%82%99%EC%84%B1%EB%8C%80%EC%95%84%ED%8C%8C%ED%8A%B8&appname=com.example.aiml_mobile_2024");
//        WalkRouteData.put("label3", "nmap://route/walk?slat=37.4640070&slng=126.9522394&sname=%EC%84%9C%EC%9A%B8%EB%8C%80%ED%95%99%EA%B5%90&dlat=37.4764356&dlng=126.9618302&dname=%EB%8F%99%EC%9B%90%EB%82%99%EC%84%B1%EB%8C%80%EC%95%84%ED%8C%8C%ED%8A%B8&appname=com.example.aiml_mobile_2024");
        WalkRouteData.put("label4", furl);

        return WalkRouteData;
    }
}
