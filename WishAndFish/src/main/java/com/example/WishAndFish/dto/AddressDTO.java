package com.example.WishAndFish.dto;

public class AddressDTO {

    private String street;
    private String streetNumber;
    private String postalCode;
    private double longitude;
    private double latitude;
    private String cityName;
    private String countryName;

    public AddressDTO() {
    }

    public AddressDTO(String street, String streetNumber, String postalCode, double longitude, double latitude, String cityName, String countryName) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
