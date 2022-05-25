package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.FishingAdventure;
import com.example.WishAndFish.repository.FishingAdventureRepository;
import com.example.WishAndFish.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        for(FishingAdventure f : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(!f.isDeleted()){
                ret.add(new FishingAdventureDTO(f));}
        };

        return ret;
    }

    public List<FishingAdventureDTO> mapSearch(FishingAdventureDTO dto) {
        List<FishingAdventureDTO> ret = new ArrayList<>();

        for(FishingAdventure a : search(dto)){

            ret.add(new FishingAdventureDTO(a));
        }

        return ret;
    }

    public List<FishingAdventure> search(FishingAdventureDTO dto) {
        List<FishingAdventure> ret = new ArrayList<>();
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
        for(FishingAdventure a : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            if(checkAdventureForSearch(a,dto,rating, price) && !a.isDeleted()){
                ret.add(a);}
        }

        return ret;
    }

    private boolean checkAdventureForSearch(FishingAdventure c, FishingAdventureDTO dto, double rating, double price){

        return checkStrings(c.getName(), dto.getName()) && checkStrings(c.getFishingInstructor().getName(), dto.getInstructor()) && checkStrings(c.getAddress().toString(), dto.getAddress()) && checkRating(c.getRating(), rating) && checkPrice(c.getPricePerDay(), price);
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
        for(FishingAdventure c : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
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
        for(FishingAdventure a : fishingAdventureRepository.findAll((Sort.by(Sort.Direction.ASC, "pricePerDay")))){
            FishingAdventureDTO adventure = new FishingAdventureDTO(a);
            adventure.setIsSubscribed(clientService.checkAdventureExistence(email, a.getId()));

            if(checkAdventureForSearch(a,dto,rating, price) && !a.isDeleted()){
                ret.add(adventure);}
        }

        return ret;

    }

    public List<FishingAdventureDTO> searchAppointments(AppointmentSearchDTO criteria){

        FishingAdventureDTO adventure = new FishingAdventureDTO(criteria.getName(), criteria.getAddress(), criteria.getRating(), criteria.getPrice());
        AppointmentDTO appointment = new AppointmentDTO(criteria.getStartDate(), criteria.getEndDate(), criteria.getMaxPersons());


        List<FishingAdventure> adventures = search(adventure);
        List<FishingAdventureDTO> ret = new ArrayList<>();

        for(FishingAdventure a : adventures){

            if(findAppointments(a.getAppointments(), appointment)){
                ret.add(new FishingAdventureDTO(a));
            }

        }

        return ret;
    }

    private boolean findAppointments(Set<Appointment> appointments, AppointmentDTO criteria) {

        for(Appointment a : appointments){
            if(a.getReserved() || a.isDeleted() || a.getIsAction() || (criteria.getMaxPersons()!=null && criteria.getMaxPersons() > a.getMaxPersons())){
                continue;
            }
            if(a.getStartDate().toLocalDate().isAfter(criteria.getStartDate().toLocalDate())){
                continue;
            }
            if(a.getEndDate().toLocalDate().isBefore(criteria.getEndDate().toLocalDate())){
                continue;
            }
            return true;
        }

        return false;
    }
}
