package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;

    public ClientDTO(Client c){
        this.id = c.getId();
        this.email = c.getEmail();
        this.name = c.getName();
        this.surname = c.getSurname();
        this.phoneNumber = c.getPhoneNumber();
    }
}
