package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.CottageOwnerRepository;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageService {

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;


    @Autowired
    private UserRepository userRepository;

    public List<CottageDTO> findAll() {

        List<CottageDTO> ret = new ArrayList<>();
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(!c.isDeleted()){
            ret.add(new CottageDTO(c));}
        };

        return ret;
    }

    public List<CottageDTO> search(CottageDTO dto) {
        List<CottageDTO> ret = new ArrayList<>();
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
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(checkCottageForSearch(c,dto,rating, price) && !c.isDeleted()){
            ret.add(new CottageDTO(c));}
        }

        return ret;
    }

    private boolean checkCottageForSearch(Cottage c, CottageDTO dto,double rating, double price){

        return checkStrings(c.getName(), dto.getName()) && checkStrings(c.getAddress().toString(), dto.getAddress()) && checkRating(c.getRating(), rating) && checkPrice(c.getPricePerDay(), price);
    }

    private boolean checkStrings(String cottage, String search){
        if(search==null){
            return true;
        }
        return search.isEmpty() || cottage.toLowerCase().contains(search.toLowerCase());
    }

    private boolean checkRating(Double cottage, Double search){
        return cottage >= search || search > 5;
    }

    private boolean checkPrice(Double cottage, Double search){
        return cottage <= search || cottage <= 0 || search == 0;
    }

    public Cottage addCottage(AddCottageDTO newCottage){
        Address address = new Address(newCottage.getAddress().getStreet(),newCottage.getAddress().getStreetNumber(),
                newCottage.getAddress().getPostalCode(),newCottage.getAddress().getCityName(),newCottage.getAddress().getCountryName(),
                newCottage.getAddress().getLongitude(),newCottage.getAddress().getLatitude());

        Cottage cottage = new Cottage(newCottage.getName(),newCottage.getDescription(),newCottage.getPrice(),address, null);
        User user = this.userRepository.findByEmail(newCottage.getOwnerEmail());

        for(CottageOwner c: this.cottageOwnerRepository.findAll()){
            if(c.getEmail().equals(user.getEmail())){
                cottage.setCottageOwner(c);
            }
        }
        return this.cottageRepository.save(cottage);
    }

    public ResponseEntity<Long> deleteCottage(Long id){
        for(Cottage c: this.cottageRepository.findAll()){
            if(c.getId() == id) {
                for(Appointment a:c.getAppointments()){
                    if(a.getReserved()){
                        System.out.println("Rezervisano");
                        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
                    }
                }
                c.setDeleted(true);
                this.cottageRepository.save(c);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    public Cottage findCottage(Long id){
        System.out.println("ISPISUJEM ID: " + id);
        return cottageRepository.findById(id).orElseGet(null);
    }


    public EditCottageDTO editBasicInfo(EditCottageDTO editedCottage){
        for (Cottage c: cottageRepository.findAll()){
            if(editedCottage.getId().equals(c.getId())){
                c.setName(editedCottage.getName());
                c.setDescription(editedCottage.getDescription());
                c.setPricePerDay(editedCottage.getPricePerDay());
                c.getAddress().setCityName(editedCottage.getAddress().getCityName());
                c.getAddress().setCountryName(editedCottage.getAddress().getCountryName());
                c.getAddress().setLatitude(editedCottage.getAddress().getLatitude());
                c.getAddress().setLongitude(editedCottage.getAddress().getLongitude());
                c.getAddress().setPostalCode(editedCottage.getAddress().getPostalCode());
                c.getAddress().setStreet(editedCottage.getAddress().getStreet());
                c.getAddress().setStreetNumber(editedCottage.getAddress().getStreetNumber());
                c.setNumberOfRooms(editedCottage.getNumberOfRooms());
                c.setBedsPerRoom(editedCottage.getBedsPerRoom());
                cottageRepository.save(c);
                return new EditCottageDTO(c);
            }
        }
        return null;
    }
}
