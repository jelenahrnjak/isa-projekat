package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private String street;
    private String streetNumber;
    private String postalCode;
    private double longitude;
    private double latitude;
    private String cityName;
    private String countryName;


    public AddressDTO(Address a){
        this.street = a.getStreet();
        this.streetNumber = a.getStreetNumber();
        this.postalCode = a.getPostalCode();
        this.longitude = a.getLongitude();
        this.latitude = a.getLatitude();
        this.cityName = a.getCityName();
        this.countryName = a.getCountryName();
    }


}
