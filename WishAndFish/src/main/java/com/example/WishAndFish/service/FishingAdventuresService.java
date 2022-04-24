package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AppointmentSearchDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.dto.FishingAdventureDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.FishingAdventure;
import com.example.WishAndFish.repository.FishingAdventureRepository;
import com.example.WishAndFish.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishingAdventuresService {

    @Autowired
    private FishingAdventureRepository fishingAdventureRepository;

    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;

    @Autowired
    private ClientService clientService;

    public List<FishingAdventureDTO> findAll() {

        List<FishingAdventureDTO> ret = new ArrayList<>();
        for(FishingAdventure f : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            if(!f.isDeleted()){
                ret.add(new FishingAdventureDTO(f));}
        };

        return ret;
    }

    public List<FishingAdventureDTO> search(FishingAdventureDTO dto) {
        List<FishingAdventureDTO> ret = new ArrayList<>();
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
        for(FishingAdventure a : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            if(checkAdventureForSearch(a,dto,rating, price) && !a.isDeleted()){
                ret.add(new FishingAdventureDTO(a));}
        }

        return ret;
    }

    private boolean checkAdventureForSearch(FishingAdventure c, FishingAdventureDTO dto, double rating, double price){

        return checkStrings(c.getName(), dto.getName()) && checkStrings(c.getFishingInstructor().getName(), dto.getInstructor()) && checkStrings(c.getAddress().toString(), dto.getAddress()) && checkRating(c.getRating(), rating) && checkPrice(c.getPricePerHour(), price);
    }

    private boolean checkStrings(String adventure, String search){
        if(search==null){
            return true;
        }
        return search.isEmpty() || adventure.toLowerCase().contains(search.toLowerCase());
    }

    private boolean checkRating(Double adventure, Double search){
        return adventure >= search || search > 5;
    }

    private boolean checkPrice(Double adventure, Double search){
        return adventure <= search || adventure <= 0 || search == 0;
    }

    public List<FishingAdventureDTO> findAllClient(String email) {

        List<FishingAdventureDTO> ret = new ArrayList<>();
        for(FishingAdventure c : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            FishingAdventureDTO adventure = new FishingAdventureDTO(c);
            adventure.setIsSubscribed(clientService.checkAdventureExistence(email, c.getId()));
            if(!c.isDeleted()){
                ret.add(adventure);}

        };

        return ret;

    }

    public List<FishingAdventureDTO> searchClient(FishingAdventureDTO dto, String email) {

        List<FishingAdventureDTO> ret = new ArrayList<>();
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
        for(FishingAdventure a : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            FishingAdventureDTO adventure = new FishingAdventureDTO(a);
            adventure.setIsSubscribed(clientService.checkAdventureExistence(email, a.getId()));

            if(checkAdventureForSearch(a,dto,rating, price) && !a.isDeleted()){
                ret.add(adventure);}
        }

        return ret;

    }

    public List<CottageDTO> searchAppointments(AppointmentSearchDTO data) {
        return new ArrayList<>();
    }
}
