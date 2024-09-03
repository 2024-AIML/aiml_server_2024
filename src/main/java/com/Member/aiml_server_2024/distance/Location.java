//package com.Member.aiml_server_2024.distance;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@NoArgsConstructor
//@Getter
//@Setter
////@ToString
//public class Location {
//
////    private String latitude;
////    private String longitude;
////    private String location;
//
//    private String name;
//}

package com.Member.aiml_server_2024.distance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Location {

    private double latitude;    // 위도
    private double longitude;   // 경도
    private String location;    // 위치 이름

    public Location(double latitude, double longitude, String location) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }
}
