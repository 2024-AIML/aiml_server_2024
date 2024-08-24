package com.Member.aiml_server_2024.model;

public class Shelter {

    public String infraName;
    public String location;
    public Double area;
    public Integer capacity;
    public String fullAddress;
    private Double latitude_EPSG4326;
    private Double longitude_EPSG4326;

    public String getInfraName() {
        return infraName;
    }

    public void setInfraName(String infraName) {
        this.infraName = infraName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getFullAddress(){
        return fullAddress;
    }

    public void setFullAddress(String fullAddress){
        this.fullAddress = fullAddress;
    }

    public Double getLatitude_EPSG4326(){
        return latitude_EPSG4326;
    }

    public void setLatitude_EPSG4326(Double latitude_EPSG4326) {
        this.latitude_EPSG4326 = latitude_EPSG4326;
    }

    public Double getLongitude_EPSG4326(){
        return longitude_EPSG4326;
    }

    public void setLongitude_EPSG4326(Double longitude_EPSG4326) {
        this.longitude_EPSG4326 = longitude_EPSG4326;
    }

    public String getFirstTwoWords() {
        String[] words = fullAddress.split("\\s+");
        if (words.length < 3) {
            return fullAddress; // Return full address if it's two words or less
        }
        return String.join(" ", words[0], words[1]);
    }
}
