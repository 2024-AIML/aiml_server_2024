package com.Member.aiml_server_2024.navigation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLocation {

    private String id;
    private String locationName;
    private String latitude;
    private String longitude;

    public UserLocation(String id, String locationName, String latitude, String longitude) {
        this.id = id;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
