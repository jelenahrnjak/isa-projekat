package com.example.WishAndFish.service;

import java.util.List;

import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.repository.UserRepository;
import com.example.WishAndFish.security.auth.model.Address;
import com.example.WishAndFish.security.auth.model.Role;
import com.example.WishAndFish.security.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    public User findOne(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(UserDTO requestUser) {
        AddressDTO a = requestUser.getAddress();
        User user = new User(requestUser.getPassword(), requestUser.getEmail(), requestUser.getName(), requestUser.getSurname(), requestUser.getPhoneNumber());
        Address address = new Address(a.getStreet(),a.getStreetNumber(),a.getPostalCode(),a.getCityName(), a.getCountryName(),a.getLongitude(),a.getLatitude());
        user.setAddress(address);

        Role role = roleService.findByName(requestUser.getRoleName());
        user.setRole(role);

        return this.userRepository.save(user);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email);}
}
