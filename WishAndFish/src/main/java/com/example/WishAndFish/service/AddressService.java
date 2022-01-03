package com.example.WishAndFish.service;

import java.util.List;

import com.example.WishAndFish.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.WishAndFish.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address findOne(Integer id) {
        return addressRepository.findById(id).orElseGet(null);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address save(Address exam) {
        return addressRepository.save(exam);
    }

    public void remove(Integer id) {
        addressRepository.deleteById(id);
    }
}
