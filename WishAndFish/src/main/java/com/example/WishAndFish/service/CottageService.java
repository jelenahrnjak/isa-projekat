package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CottageService {

    @Autowired
    private CottageRepository cottageRepository;

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }
}
