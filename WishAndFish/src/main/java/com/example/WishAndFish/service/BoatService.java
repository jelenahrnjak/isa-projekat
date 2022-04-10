package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AddBoatDTO;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.BoatOwnerRepository;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.ClientRepository;
import com.example.WishAndFish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    public List<BoatDTO> findAll() {

        List<BoatDTO> ret = new ArrayList<>();
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            if(!b.isDeleted()){
                ret.add(new BoatDTO(b));}

        };

        return ret;
    }

    public List<BoatDTO> findAllClient(String email) {

        List<BoatDTO> ret = new ArrayList<>();
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            BoatDTO boat = new BoatDTO(b);
            boat.setIsSubscribed(clientService.checkBoatExistence(email, b.getId()));
            if(!b.isDeleted()){
                ret.add(boat);}

        };

        return ret;
    }

    public List<BoatDTO> search(BoatDTO dto) {
        List<BoatDTO> ret = new ArrayList<>();
        double rating = 0;
        double price = 0;
        try{
            rating = Double.parseDouble(dto.getRating());
        }catch (Exception e){
            System.out.println("Error with parsing rating");
        }
        try{
            price = Double.parseDouble(dto.getPrice());
        }catch (Exception e){
            System.out.println("Error with parsing price");
        }
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            if(checkBoatForSearch(b,dto,rating, price) && !b.isDeleted()){
                ret.add(new BoatDTO(b));}
        }

        return ret;
    }

    private boolean checkBoatForSearch(Boat b, BoatDTO dto,double rating, double price){

        return checkStrings(b.getName(), dto.getName()) && checkStrings(b.getAddress().toString(), dto.getAddress()) && checkRating(b.getRating(), rating) && checkPrice(b.getPricePerHour(), price);
    }

    private boolean checkStrings(String boat, String search){
        if(search==null){
            return true;
        }
        return search.isEmpty() || boat.toLowerCase().contains(search.toLowerCase());
    }

    private boolean checkPrice(Double cottage, Double search){
        return cottage <= search || cottage <= 0 || search == 0;
    }

    private boolean checkRating(Double boat, Double search){
        return boat >= search || search > 5;
    }

    public AddBoatDTO addBoat(AddBoatDTO newBoat){
        Address address = new Address(newBoat.getAddress().getStreet(),newBoat.getAddress().getStreetNumber(),
                newBoat.getAddress().getPostalCode(),newBoat.getAddress().getCityName(),newBoat.getAddress().getCountryName(),
                newBoat.getAddress().getLongitude(),newBoat.getAddress().getLatitude());

        Boat boat = new Boat(newBoat.getName(),newBoat.getType(),newBoat.getLength(),newBoat.getEngineNumber(), newBoat.getEnginePower(), newBoat.getMaxSpeed(), address,
                newBoat.getDescription(),newBoat.getCapacity(), newBoat.getPricePerHour());
        User user = this.userRepository.findByEmail(newBoat.getOwnerEmail());

        for(BoatOwner b: this.boatOwnerRepository.findAll()){
            if(b.getEmail().equals(user.getEmail())){
                boat.setBoatOwner(b);
            }
        }
        this.boatRepository.save(boat);
        return newBoat;
    }


    public ResponseEntity<Long> deleteBoat(Long id){
        for(Boat b: this.boatRepository.findAll()){
            if(b.getId() == id) {
                if(b.getAppointments() != null) {
                    for(Appointment a:b.getAppointments()){
                        if(a.getReserved()){
                            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
                        }
                    }
                }
                b.setDeleted(true);
                this.boatRepository.save(b);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    public List<BoatDTO> searchClient(BoatDTO dto, String email) {
        List<BoatDTO> ret = new ArrayList<>();
        double rating = 0;
        double price = 0;
        try{
            rating = Double.parseDouble(dto.getRating());
        }catch (Exception e){
            System.out.println("Error with parsing rating");
        }
        try{
            price = Double.parseDouble(dto.getPrice());
        }catch (Exception e){
            System.out.println("Error with parsing price");
        }
        for(Boat b : boatRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            BoatDTO boat = new BoatDTO(b);
            boat.setIsSubscribed(clientService.checkBoatExistence(email, b.getId()));
            if(checkBoatForSearch(b,dto,rating, price) && !b.isDeleted()){
                ret.add(boat);}
        }

        return ret;
    }
}
