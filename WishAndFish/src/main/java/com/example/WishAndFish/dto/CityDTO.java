package com.example.WishAndFish.dto;

public class CityDTO {

    private String cityName;
    private CountryDTO country;

    public CityDTO() {
    }

    public CityDTO(String cityName, CountryDTO country) {
        this.cityName = cityName;
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }
}
