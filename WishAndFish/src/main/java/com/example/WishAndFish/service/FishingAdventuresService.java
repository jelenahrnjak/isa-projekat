package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.FishingAdventureDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.FishingAdventure;
import com.example.WishAndFish.model.FishingInstructor;
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
}
