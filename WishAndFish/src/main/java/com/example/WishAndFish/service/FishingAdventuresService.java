package com.example.WishAndFish.service;

import com.example.WishAndFish.model.FishingInstructor;
import com.example.WishAndFish.repository.FishingAdventureRepository;
import com.example.WishAndFish.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishingAdventuresService {

    @Autowired
    private FishingAdventureRepository fishingAdventureRepository;

    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;
}
