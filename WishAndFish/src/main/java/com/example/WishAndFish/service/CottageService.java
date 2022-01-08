package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.dto.CottagesSearchDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageService {

    @Autowired
    private CottageRepository cottageRepository;

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
}
