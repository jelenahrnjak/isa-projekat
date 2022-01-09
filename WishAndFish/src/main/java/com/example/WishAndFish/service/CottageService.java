package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CottageService {

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CottageDTO> findAll() {

        List<CottageDTO> ret = new ArrayList<CottageDTO>();
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "name")))){
            ret.add(new CottageDTO(c));
        };

        return ret;
    }

    public List<CottageDTO> search(CottagesSearchDTO dto) {
        List<CottageDTO> ret = new ArrayList<CottageDTO>();
        double rating = 0;
        try{
            rating = Double.parseDouble(dto.getRating());
        }catch (Exception e){
            System.out.println("Error with parsing rating");
        }
        for(Cottage c : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "name")))){
            if(checkCottageForSearch(c,dto,rating)){
            ret.add(new CottageDTO(c));}
        }

        return ret;
    }

    private boolean checkCottageForSearch(Cottage c, CottagesSearchDTO dto,double rating){

        if(checkStrings(c.getName(),dto.getName()) && checkStrings(c.getDescription(),dto.getDescription()) && checkStrings(c.getAddress().toString(),dto.getAddress()) && checkRating(c.getRating(),rating)){
            return true;
        }
        return false;
    }

    private boolean checkStrings(String cottage, String search){
        if(search==null){
            return true;
        }
        if(search.isEmpty() || cottage.toLowerCase().contains(search.toLowerCase())){
            return true;
        }
        return false;
    }

    private boolean checkRating(Double cottage, Double search){
        if(cottage >= search || cottage==0 || search > 5){
            return true;
        }
        return false;
    }

    public Cottage addCottage(AddCottageDTO newCottage){
        System.out.println("Nova vikendica" + newCottage.getDescription());
        Address address = new Address(newCottage.getAddress().getStreet(),newCottage.getAddress().getStreetNumber(),
                newCottage.getAddress().getPostalCode(),newCottage.getAddress().getCityName(),newCottage.getAddress().getCountryName(),
                newCottage.getAddress().getLongitude(),newCottage.getAddress().getLatitude());

        User user = this.userRepository.findByEmail(newCottage.getOwnerEmail());
        CottageOwner cottageOwner = new CottageOwner(user);

        Cottage cottage = new Cottage(newCottage.getName(),newCottage.getDescription(),newCottage.getPrice(),address, null);

        cottage.setCottageOwner(cottageOwner);
        return this.cottageRepository.save(cottage);
    }
}
