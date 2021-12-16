package com.example.WishAndFish.server;

import java.util.List;

import com.example.WishAndFish.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.WishAndFish.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country findOne(Integer id) {
        return countryRepository.findById(id).orElseGet(null);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country save(Country exam) {
        return countryRepository.save(exam);
    }

    public void remove(Integer id) {
        countryRepository.deleteById(id);
    }
}
