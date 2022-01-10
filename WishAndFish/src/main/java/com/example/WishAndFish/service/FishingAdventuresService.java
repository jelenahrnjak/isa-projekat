package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.FishingAdventureDTO;
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

    public List<FishingAdventureDTO> findAll() {

        List<FishingAdventureDTO> ret = new ArrayList<FishingAdventureDTO>();
        for(FishingAdventure f : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerHour")))){
            if(!f.isDeleted()){
                ret.add(new FishingAdventureDTO(f));}
        };

        return ret;
    }

    public List<FishingAdventureDTO> search(FishingAdventureDTO dto) {
        List<FishingAdventureDTO> ret = new ArrayList<FishingAdventureDTO>();
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

        if(checkStrings(c.getName(),dto.getName()) && checkStrings(c.getFishingInstructor().getName(),dto.getInstructor()) && checkStrings(c.getAddress().toString(),dto.getAddress()) && checkRating(c.getRating(),rating) && checkPrice(c.getPricePerHour(), price)){
            return true;
        }
        return false;
    }

    private boolean checkStrings(String adventure, String search){
        if(search==null){
            return true;
        }
        if(search.isEmpty() || adventure.toLowerCase().contains(search.toLowerCase())){
            return true;
        }
        return false;
    }

    private boolean checkRating(Double adventure, Double search){
        if(adventure >= search || search > 5){
            return true;
        }
        return false;
    }

    private boolean checkPrice(Double adventure, Double search){
        if(adventure <= search || adventure<=0 || search == 0){
            return true;
        }
        return false;
    }
}
