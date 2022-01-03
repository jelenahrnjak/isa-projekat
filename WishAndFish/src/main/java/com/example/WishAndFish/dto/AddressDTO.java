package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.User;

import javax.persistence.Column;

public class AddressDTO {

    private String street;
    private String streetNumber;
    private String postalCode;
    private String longitude;
    private String latitude;
    private CityDTO city;

    public AddressDTO() {
    }

    public AddressDTO(String street, String streetNumber, String postalCode, String longitude, String latitude, CityDTO city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
