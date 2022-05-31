package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "street", unique = false, nullable = true)
    private String street;
    @Column(name = "streetNumber", unique = false, nullable = true)
    private String streetNumber;
    @Column(name = "postalCode", unique = false, nullable = true)
    private String postalCode;
    @Column(unique = false, nullable = true)
    private String cityName;
    @Column(unique = false, nullable = true)
    private String countryName;
    @Column(name = "longitude", unique = false, nullable = true)
    private double longitude;
    @Column(name = "latitude", unique = false, nullable = true)
    private double latitude;


    public Address(String street, String streetNumber, String postalCode, String cityName, String countryName, double longitude, double latitude) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.countryName = countryName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Address(String street, String streetNumber, String postalCode, String cityName, String countryName) {

        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    @Override
    public String toString(){
        return this.street + " " + this.streetNumber + ", " + this.cityName + ", " + this.countryName;
    }


}
