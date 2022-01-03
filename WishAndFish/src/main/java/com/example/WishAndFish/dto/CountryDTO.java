package com.example.WishAndFish.dto;

public class CountryDTO {

    private String countryName;

    public CountryDTO() {
    }

    public CountryDTO(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
