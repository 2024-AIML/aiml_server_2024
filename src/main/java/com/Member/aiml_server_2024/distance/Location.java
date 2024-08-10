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

    private String name;

    // 생성자 추가
    public Location(String name) {
        this.name = name;
    }

    // equals() 및 hashCode()를 오버라이드 하는 것이 좋습니다. 테스트에서 객체 비교를 위해서입니다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return name != null ? name.equals(location.name) : location.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
