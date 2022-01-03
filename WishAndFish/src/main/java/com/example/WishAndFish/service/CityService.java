package com.example.WishAndFish.service;

import java.util.List;

import com.example.WishAndFish.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.WishAndFish.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City findOne(Integer id) {
        return cityRepository.findById(id).orElseGet(null);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City save(City exam) {
        return cityRepository.save(exam);
    }

    public void remove(Integer id) {
        cityRepository.deleteById(id);
    }
}
