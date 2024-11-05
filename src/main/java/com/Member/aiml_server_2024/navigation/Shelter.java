package com.Member.aiml_server_2024.navigation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Shelter {
    private String Sheltername;
    private String latitude;
    private String longitude;

    public Shelter(String Sheltername, String latitude, String longitude) {
        this.Sheltername = Sheltername;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
