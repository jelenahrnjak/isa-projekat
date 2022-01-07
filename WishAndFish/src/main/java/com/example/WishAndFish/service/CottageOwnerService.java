package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AddressDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.model.Address;
import com.example.WishAndFish.model.CottageOwner;
import com.example.WishAndFish.model.Role;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CottageOwnerService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    LoyaltyCategoryRepository loyaltyCategoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    public User save(UserDTO requestUser) {
        AddressDTO a = requestUser.getAddress();
        CottageOwner user = new CottageOwner(passwordEncoder.encode(requestUser.getPassword()), requestUser.getEmail(), requestUser.getName(), requestUser.getSurname(), requestUser.getPhoneNumber());
        Address address = new Address(a.getStreet(),a.getStreetNumber(),a.getPostalCode(),a.getCityName(), a.getCountryName(),a.getLongitude(),a.getLatitude());
        user.setAddress(address);
        user.setLoyaltyCategory(loyaltyCategoryRepository.findByLevel(1));
        user.setVerificationCode(requestUser.getVerificationCode());
        user.setReasonForRegistration(requestUser.getReasonForRegistration());

        Role role = roleRepository.findByName(requestUser.getRoleName());
        user.setRole(role);

        return this.cottageOwnerRepository.save(user);
    }

}
